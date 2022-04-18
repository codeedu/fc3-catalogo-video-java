package com.fullcycle.CatalogoVideo.application.category.common;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fullcycle.CatalogoVideo.domain.category.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryOutputData {

    private UUID id;
    private String name;
    private String description;
    private Boolean isActive;
    private LocalDateTime createdAt;  
    private LocalDateTime updatedAt;  
    private LocalDateTime deletedAt;  

    public static CategoryOutputData fromDomain(final Category domain) {
        return new CategoryOutputData(
            domain.getId(), 
            domain.getName(), 
            domain.getDescription(), 
            domain.getIsActive(),
            domain.getCreatedAt(),
            domain.getUpdatedAt(),
            domain.getDeletedAt()
        );
    }
}

