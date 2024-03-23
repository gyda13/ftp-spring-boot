
# FTP (File Transfer Protocol)

This repository is designed for the article available at [Establishing an FTP Server Using Docker and Accessing It via Spring Boot](https://medium.com/@gydamohaimeed/b9a87b389bb5). For detailed explanations and context, please refer to the article.

### Before Running the Application

1. **Edit the Docker Compose File**: Open the `docker-compose.yml` file and locate the `volumes` section. Replace `/Users/gydaalmohaimeed/Documents/ftp` with the path to your desired directory on your host machine, ensuring you provide your own username in place of `gydaalmohaimeed`.

2. **Run the Container**: Navigate to the project directory in the terminal. Then, execute the following command to start the FTP server container:


```bash
docker-compose up
 ```
