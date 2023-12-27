package com.fileupload.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String itemName;

    private Integer quantity;

    @Column(nullable = true)
    private Long fileId;

    @Builder
    public Item(String itemName, Integer quantity, Long fileId) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.fileId = fileId;
    }
}