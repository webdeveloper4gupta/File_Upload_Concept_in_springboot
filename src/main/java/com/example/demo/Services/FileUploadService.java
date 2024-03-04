package com.example.demo.Services;


import com.example.demo.Entity.FileUploadEntity;
import com.example.demo.Repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

    @Autowired
    private AttachmentRepository repo;

    public FileUploadEntity  uploadFile(MultipartFile file) throws Exception{
        String fileName= StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println(fileName);
        try{
            if(fileName.contains("..")){
                throw new Exception("FileName contains invalid path sequence");
            }
            FileUploadEntity f=new FileUploadEntity(fileName,file.getContentType(),file.getBytes());
//            System.out.println("File is going to save ");
            return repo.save(f);
        }
        catch(Exception error){
            throw new Exception("Could not save file");
        }
    }



    public FileUploadEntity getAttachment(String fieldId) throws Exception{
        return repo.findById(fieldId).orElseThrow(()->new Exception("File not found with Id"));
    }
}
