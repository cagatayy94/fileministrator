package com.fileministrator.web.business;

import com.fileministrator.web.entity.File;

import java.util.List;

public interface FileManager {
    List<File> getAll();
    File get(Integer fileId);
    void delete(Integer fileId);
    void update(File file);
    void create(File file);

}
