package com.springboot.web.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {

    private final String UPLOAD_DIR;

    public FileUploadHelper(@Value("${file.upload.dir}") String uploadDir) throws IOException {
        this.UPLOAD_DIR = uploadDir;

        // Ensure the directory exists
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new IOException("Could not create upload directory: " + UPLOAD_DIR);
            }
        }
    }

    public boolean uploadFile(MultipartFile multipartFile) {
        boolean f = false;
        try {
            Files.copy(
                multipartFile.getInputStream(),
                Paths.get(UPLOAD_DIR + File.separator + multipartFile.getOriginalFilename()),
                StandardCopyOption.REPLACE_EXISTING
            );
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}
