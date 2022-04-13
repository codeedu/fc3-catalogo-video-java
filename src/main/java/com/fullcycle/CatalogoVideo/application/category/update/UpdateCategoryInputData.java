package com.fullcycle.CatalogoVideo.application.category.update;

import lombok.Data;

@Data
public class UpdateCategoryInputData {
    private String name;
    private String description;
    private Boolean isActive;
}
