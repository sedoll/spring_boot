package com.example.spring31.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    ADMIN("ADMIN"), TEACHER("TEACHER"), USER("USER");
    private String value;
}
