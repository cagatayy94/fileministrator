package com.fileministrator.web.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class File {
    @Id
    private Integer id;
    @Column
    private String name;
    @Column
    private String extension;
    @Column
    private String path;
    @Column
    private String size;
    @Column
    private Byte[] file;
}