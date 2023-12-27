package com.fileupload.request;

import com.fileupload.domain.File;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FileRequest {
    @NotEmpty
    private Long id;
    @NotNull
    private String originFileName;
    @NotNull
    private String fullPath;
    @NotEmpty
    private MultipartFile file;
}
