package com.fileministrator.web.business;

import com.fileministrator.web.entity.File;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface FileManager {
    List<File> getAll();
    File getFirstById(Integer fileId);
    void delete(Integer fileId);
    void update(Integer fileId, MultipartFile multipartFile) throws IOException;
    void create(MultipartFile multipartFile) throws IOException;
    byte[] getContentById(Integer fileId) throws IOException;
}
