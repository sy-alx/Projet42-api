package com.exemple.Projet42_api.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FileStorageService {

    private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);
    private final Path rootLocation = Paths.get("uploads");

    public FileStorageService() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage location", e);
        }
    }

    public void store(MultipartFile file, String fileName) throws IOException {
        logger.info("Storing file: " + fileName);
        Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName));
    }

    public void delete(String fileName) {
        try {
            Path filePath = this.rootLocation.resolve(fileName);
            logger.info("Attempting to delete file: " + filePath.toString());
            boolean deleted = Files.deleteIfExists(filePath);
            if (deleted) {
                logger.info("File deleted successfully: " + filePath.toString());
            } else {
                logger.warn("File not found, could not delete: " + filePath.toString());
            }
        } catch (IOException e) {
            logger.error("Could not delete file: " + fileName, e);
            throw new RuntimeException("Could not delete file: " + fileName, e);
        }
    }
}
