package com.fullcycle.CatalogoVideo.application.category.create;

import com.fullcycle.CatalogoVideo.domain.category.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreateCategoryInputData {
    
    private String name;
    private String description;
    private Boolean isActive = true;

    public Category toDomain() {
        return Category.newCategory(name, description, isActive);
    }
}
