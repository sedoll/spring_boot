package com.chunjae.jpa1.dto.upload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadResultDTO {
    private String uuid;
    private String filename;
    private boolean img;
    public String getLink() {
        if(img) {
            return "s_" + uuid + "_" + filename; // 이미지인 경우 썸네일로 활용
        } else {
            return uuid+"_"+filename;
        }
    }
}
