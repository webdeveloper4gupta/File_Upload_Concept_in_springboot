package com.example.demo.Controller;


import com.example.demo.Entity.FileUploadEntity;
import com.example.demo.ResponseData;
import com.example.demo.Services.FileUploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.core.io.Resource;
@RestController
public class FileUpload {

    @Autowired
    private FileUploadService fileUploadService;



    @PostMapping("/upload")
    public ResponseData uploadFile(@RequestParam("file")MultipartFile file)throws Exception{
        String downloadUri="";
        FileUploadEntity fileUploadEntity=fileUploadService.uploadFile(file);
            downloadUri =ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path( fileUploadEntity.getId()).toUriString();
            return new ResponseData(fileUploadEntity.getFileName(),downloadUri,file.getContentType(),file.getSize());
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
       FileUploadEntity attachment = null;
        attachment = fileUploadService.getAttachment(fileId);
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName()
                                + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }

}
