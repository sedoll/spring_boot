package com.fileupload.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class File {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String originFileName;
    @Column(nullable = false)
    private String fullPath;

    @Builder
    public File(Long id, String originFileName, String fullPath) {
        this.id = id;
        this.originFileName = originFileName;
        this.fullPath = fullPath;
    }
}