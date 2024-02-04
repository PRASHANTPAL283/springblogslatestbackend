package com.SpringBlogsLatestBE.SpringBlogsLatestBE.DTOs;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="fileEntityModel")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int fileId;
    public String fileName;
    public String fileType;

    @Lob
    public byte[] data;

}