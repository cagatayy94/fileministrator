package com.fileministrator.web.api;

import com.fileministrator.web.business.FileManager;
import com.fileministrator.web.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/file")
public class FileController {

    @Autowired
    FileManager fileManager;

    @GetMapping
    ResponseEntity<List<File>>getFiles(){
        return ResponseEntity.ok(
                this.fileManager.getAll()
        ) ;
    }

    @GetMapping
    ResponseEntity<File>getOne(Integer id){
        return ResponseEntity.ok(
                this.fileManager.get(id)
        ) ;
    }
}
