package com.fileministrator.web.business;

import com.fileministrator.web.entity.File;
import com.fileministrator.web.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Service
public class FileManagerImpl implements FileManager{

    private final String FILE_SIZE_ERROR = "File size should be lower than 5mb";
    private final Long MAX_FILE_SIZE = 5000000L;//5mb
    private final String FILE_EXTENSION_ERROR = "Extension not available. Available extensions png, jpeg, jpg, docx, pdf, xlsx";
    private final String SAVE_PATH = "src/main/resources/files/";


    private final String[] availableExtensions = {
            "png",
            "jpeg",
            "jpg",
            "docx",
            "pdf",
            "xlsx"
    };

    FileRepository fileRepository;

    public FileManagerImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public File getFirstById(Integer id) {
        return this.fileRepository.getFirstById(id);
    }

    @Override
    public List<File> getAll() {
        return this.fileRepository.findAll();
    }

    @Override
    public void delete(Integer fileId) {
        this.fileRepository.deleteById(fileId);
    }

    @Override
    public void update(File file) {
        this.fileRepository.save(file);
    }

    @Override
    public void create(MultipartFile multipartFile) throws IOException {

        File newFile = new File();
        newFile.setName(multipartFile.getName());
        newFile.setSize(multipartFile.getSize());
        newFile.setPath(SAVE_PATH + multipartFile.getOriginalFilename());

        String[] extensionArray = multipartFile.getOriginalFilename().split("\\.");
        newFile.setExtension(extensionArray[extensionArray.length-1]);// get last element

        if (Arrays.stream(this.availableExtensions).noneMatch(Predicate.isEqual(newFile.getExtension()))){
            throw new InvalidParameterException(this.FILE_EXTENSION_ERROR);
        }

        if (multipartFile.getSize() > this.MAX_FILE_SIZE){
            throw new InvalidParameterException(this.FILE_SIZE_ERROR);
        }

        OutputStream out = new FileOutputStream(newFile.getPath());
        out.write(multipartFile.getBytes());
        out.close();

        this.fileRepository.save(newFile);
    }
}
