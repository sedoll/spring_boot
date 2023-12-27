package com.fileupload.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ItemRequest {

    @NotEmpty
    private String itemName;
    @NotNull
    @Range(min = 10, max=9999)
    private Integer qty;

    private MultipartFile file;
}
