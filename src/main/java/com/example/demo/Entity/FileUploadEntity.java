package com.example.demo.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String  id;
    @Lob
    @Column(length = 1048576)
    private byte[] data;

    private String fileName;

    private String fileType;

    public FileUploadEntity(String fileName, String contentType, byte[] bytes) {
        this.fileName=fileName;
        this.fileType=contentType;
        this.data=bytes;
    }
}
