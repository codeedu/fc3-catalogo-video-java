package com.fullcycle.CatalogoVideo.application.category.update;

import java.util.UUID;

import com.fullcycle.CatalogoVideo.application.exception.NotFoundException;
import com.fullcycle.CatalogoVideo.domain.category.Category;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UpdateCategoryUseCase implements IUpdateCategoryUseCase {

    private ICategoryGateway repository;
    
    @Override
    public void execute(UUID id, UpdateCategoryInputData input) {
        Category category = repository.findById(id)
                                      .orElseThrow(() -> new NotFoundException("Category %s not found", id));
        
        category.update(
            input.getName(), 
            input.getDescription(), 
            input.getIsActive()
        );

        repository.update(category);
    }
    
}
