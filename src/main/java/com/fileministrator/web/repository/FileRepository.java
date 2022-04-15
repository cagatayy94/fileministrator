package com.fileministrator.web.repository;

import com.fileministrator.web.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Integer> {
    File getFirstById(Integer fileId);
}
