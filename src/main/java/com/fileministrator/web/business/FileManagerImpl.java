package com.fileministrator.web.business;

import com.fileministrator.web.entity.File;
import com.fileministrator.web.repository.FileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileManagerImpl implements FileManager{

    FileRepository fileRepository;

    @Override
    public File get(Integer id) {
        return this.fileRepository.getById(id);
    }

    @Override
    public List<File> getAll() {
        return this.fileRepository.findAll();
    }

    @Override
    public void delete(Integer fileId) {
        this.fileRepository.deleteById(fileId);
        return;
    }

    @Override
    public void update(File file) {
        this.fileRepository.save(file);

    }

    @Override
    public void create(File file) {
        this.fileRepository.save(file);
    }
}
