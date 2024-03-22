package com.example.ftpspringboot;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class FtpController {

    private final FtpService ftpService;

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("uploadPath") String uploadPath) {

        try {

            ftpService.uploadFile(file.getInputStream(), uploadPath);

            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e.getMessage());
        }

    }

    @PostMapping("/download-file")
    public ResponseEntity<?> downloadFile(@RequestParam("remoteFilePath") String remoteFilePath,
                                          @RequestParam("localPath") String localPath) {
        try {
            // remoteFilePath = /uploads/hello.png , localPath = /Users/gydaalmohaimeed/Documents/hi.png
            ftpService.downloadFile(remoteFilePath, localPath);
            return ResponseEntity.status(HttpStatus.OK).body("File downloaded successfully to " + localPath);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to download file: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete-file")
    public ResponseEntity<String> deleteFile(@RequestParam("remoteFilePath") String remoteFilePath) {

        try {

            // remoteFilePath = /uploads/hello.png
            ftpService.deleteFile(remoteFilePath);

            return ResponseEntity.status(HttpStatus.OK).body("File deleted successfully.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete file: " + e.getMessage());
        }

    }


}
