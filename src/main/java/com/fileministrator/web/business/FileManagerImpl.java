package com.fileministrator.web.business;

import com.fileministrator.web.entity.File;
import com.fileministrator.web.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Service
public class FileManagerImpl implements FileManager{

    private final String FILE_SIZE_ERROR = "File size should be lower than 5mb";
    private final String FILE_EXTENSION_ERROR = "Extension not available. Available extensions png, jpeg, jpg, docx, pdf, xlsx";
    private final Long MAX_FILE_SIZE = 5000000L;//5mb
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
    public void update(
            Integer fileId,
            MultipartFile multipartFile
    ) throws IOException {
        File file = this.fileRepository.getFirstById(fileId);

        String extension = getExtensionFromMultipartFile(multipartFile);
        file.setExtension(extension);

        String fullName = multipartFile.getOriginalFilename();

        file.setName(fullName);
        file.setSize(multipartFile.getSize());
        file.setPath(SAVE_PATH + fullName);

        validateExtension(file);
        validateSize(multipartFile);
        saveFile(multipartFile, file);

        this.fileRepository.save(file);
    }

    @Override
    public void create(MultipartFile multipartFile) throws IOException {

        File newFile = new File();
        newFile.setName(multipartFile.getOriginalFilename());
        newFile.setSize(multipartFile.getSize());
        newFile.setPath(SAVE_PATH + multipartFile.getOriginalFilename());

        String extension = getExtensionFromMultipartFile(multipartFile);
        newFile.setExtension(extension);

        validateExtension(newFile);
        validateSize(multipartFile);
        saveFile(multipartFile, newFile);

        this.fileRepository.save(newFile);
    }

    @Override
    public byte[] getContentById(Integer fileId) throws IOException {
        File file = this.fileRepository.getFirstById(fileId);
        FileInputStream createFile = new FileInputStream(file.getPath());
        return createFile.readAllBytes();
    }

    private void validateExtension(File file){
        if (Arrays.stream(this.availableExtensions).noneMatch(Predicate.isEqual(file.getExtension()))){
            throw new InvalidParameterException(this.FILE_EXTENSION_ERROR);
        }
    }

    private void validateSize(MultipartFile multipartFile){
        if (multipartFile.getSize() > this.MAX_FILE_SIZE){
            throw new InvalidParameterException(this.FILE_SIZE_ERROR);
        }
    }

    private String getExtensionFromMultipartFile(MultipartFile multipartFile){
        String[] extensionArray = multipartFile.getOriginalFilename().split("\\.");
        return extensionArray[extensionArray.length-1];// get last element
    }

    private void saveFile(MultipartFile multipartFile, File file) throws IOException {
        OutputStream out = new FileOutputStream(file.getPath());
        out.write(multipartFile.getBytes());
        out.close();
    }
}