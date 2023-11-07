package com.chunjae.test06th.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    ADMIN("ADMIN"), EMP("EMP"), USER("USER");
    private String value;
}
