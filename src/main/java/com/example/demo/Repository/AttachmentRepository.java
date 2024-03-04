package com.example.demo.Repository;

import com.example.demo.Entity.FileUploadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<FileUploadEntity,String> {
}
