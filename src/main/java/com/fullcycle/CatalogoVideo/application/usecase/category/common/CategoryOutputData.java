package com.fullcycle.CatalogoVideo.application.usecase.category.common;

import java.util.UUID;

import com.fullcycle.CatalogoVideo.domain.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryOutputData {

    private UUID id;
    private String name;
    private String description;
    private Boolean isActive;    

    public static CategoryOutputData fromDomain(Category domain) {
        return new CategoryOutputData(
            domain.getId(), 
            domain.getName(), 
            domain.getDescription(), 
            domain.getIsActive()
        );
    }
}

