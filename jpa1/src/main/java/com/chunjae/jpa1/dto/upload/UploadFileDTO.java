package com.chunjae.jpa1.dto.upload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

// 업로드를 위한 DTO
@Data
public class UploadFileDTO {
    private List<MultipartFile> files;
}
