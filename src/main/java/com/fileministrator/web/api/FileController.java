package com.fileministrator.web.api;

import com.fileministrator.web.business.FileManager;
import com.fileministrator.web.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/files")
public class FileController {
    FileManager fileManager;

    @Autowired
    public FileController(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @GetMapping()
    ResponseEntity<List<File>>getAll(){
        return ResponseEntity.ok(
                this.fileManager.getAll()
        );
    }

    @GetMapping("{fileId}")
    ResponseEntity<File>getById(@PathVariable Integer fileId){
        return ResponseEntity.ok(
                this.fileManager.getFirstById(fileId)
        );
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    ResponseEntity<String> create(@RequestPart("file") MultipartFile file) {
        try {
            this.fileManager.create(file);
            return ResponseEntity.status(HttpStatus.CREATED).body("Success");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
