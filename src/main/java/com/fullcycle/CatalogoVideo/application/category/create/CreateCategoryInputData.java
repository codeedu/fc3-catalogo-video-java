package com.fullcycle.CatalogoVideo.application.category.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryInputData {
    
    private String name;
    private String description;
    private Boolean isActive = true;
}
