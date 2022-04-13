package com.fullcycle.CatalogoVideo.application.category.create;

import com.fullcycle.CatalogoVideo.application.category.common.CategoryOutputData;
import com.fullcycle.CatalogoVideo.domain.category.Category;
import com.fullcycle.CatalogoVideo.domain.category.repository.ICategoryRepository;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CreateCategoryUseCase implements ICreateCategoryUseCase {

    private ICategoryRepository repository;

    @Override
    public CategoryOutputData execute(CreateCategoryInputData input) {
        var created = repository.create(toDomain(input));
        return CategoryOutputData.fromDomain(created);
    }
    
    private Category toDomain(CreateCategoryInputData input) {
        return new Category(
            input.getName(),
            input.getDescription(),
            input.getIsActive()
        );
    }
}
