package com.fileministrator.web.api;

import com.fileministrator.web.business.FileManager;
import com.fileministrator.web.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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

    @GetMapping("{fileId}/content")
    ResponseEntity<?> getContentById(@PathVariable Integer fileId){
        try {
            byte[] content = this.fileManager.getContentById(fileId);
            File file = this.fileManager.getFirstById(fileId);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+file.getName());

            return ResponseEntity.ok()
                    .headers(httpHeaders)
                    .contentLength(file.getSize())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(content);

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{fileId}")
    ResponseEntity<?>delete(@PathVariable Integer fileId){
        this.fileManager.delete(fileId);
        return ResponseEntity.ok("Success");
    }
}
