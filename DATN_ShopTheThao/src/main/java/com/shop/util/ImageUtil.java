package com.shop.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageUtil {

    private static final String UPLOAD_DIR = "src/main/resources/static/user-page/images";

    public String saveImage(MultipartFile file, String fileName, String subfolder) throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIR, subfolder);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        deleteImagesByFileName(fileName, subfolder);

        String originalFileName = file.getOriginalFilename();
        int lastDotIndex = originalFileName.lastIndexOf(".");
        if (lastDotIndex != -1) {
            String extension = originalFileName.substring(lastDotIndex + 1);
            String fullFileName = fileName + "." + extension;

            Path filePath = uploadPath.resolve(fullFileName);
            file.transferTo(filePath);

            return fullFileName; // Trả về tên tệp đã lưu
        } else {
            // Xử lý không có phần mở rộng tệp
            throw new IllegalArgumentException("Missing file extension");
        }
    }
    
    public String saveImage(String originalFileName, String subfolder) throws IOException {
        if (originalFileName == null || originalFileName.isEmpty()) {
            throw new IllegalArgumentException("Invalid original file name");
        }
        
        Path uploadPath = Paths.get(UPLOAD_DIR, subfolder, originalFileName);
        if (!Files.exists(uploadPath.getParent())) {
            Files.createDirectories(uploadPath.getParent());
        }
        
        // Simulate saving the file by just creating an empty file
        Files.createFile(uploadPath);
        
        // Return the original file name
        return originalFileName;
    }

    
    public void deleteImagesByFileName(String fileName, String subfolder) throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIR, subfolder);
        if (!Files.exists(uploadPath)) {
            return; // Không có thư mục, không cần xóa
        }

        Files.walk(uploadPath)
             .filter(path -> path.getFileName().toString().startsWith(fileName))
             .forEach(path -> {
                 try {
                     Files.delete(path);
                 } catch (IOException e) {
                     e.printStackTrace(); // Xử lý lỗi nếu cần
                 }
             });
    }
}
