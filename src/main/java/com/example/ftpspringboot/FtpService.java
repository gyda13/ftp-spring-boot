package com.example.ftpspringboot;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FtpService {

    @Value("${ftp.host}")
    private String ftpHost;

    @Value("${ftp.port}")
    private int ftpPort;

    @Value("${ftp.username}")
    private String ftpUsername;

    @Value("${ftp.password}")
    private String ftpPassword;


    // configures and returns a connected and logged-in FTPClient.
    private FTPClient configureFtpClient() throws IOException {

        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(ftpHost, ftpPort);
        ftpClient.login(ftpUsername, ftpPassword);
        // enter passive mode
        ftpClient.enterLocalPassiveMode();
        // set file type to binary for file transfers
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        return ftpClient;
    }

    public void uploadFile(InputStream inputStream, String uploadPath) throws IOException {
        FTPClient ftpClient = configureFtpClient();
        try {
            boolean result = ftpClient.storeFile(uploadPath, inputStream);
            if (!result) {
                throw new IOException("Could not upload the file to the FTP server.");
            }
        } finally {
            // ensure FTPClient is disconnected
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        }

    }




}

