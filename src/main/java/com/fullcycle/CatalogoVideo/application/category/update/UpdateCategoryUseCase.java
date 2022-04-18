package com.fullcycle.CatalogoVideo.application.category.update;

import java.util.UUID;

import com.fullcycle.CatalogoVideo.application.exception.NotFoundException;
import com.fullcycle.CatalogoVideo.domain.category.Category;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateCategoryUseCase implements IUpdateCategoryUseCase {

    private final ICategoryGateway gateway;
    
    @Override
    public void execute(UUID id, UpdateCategoryInputData input) {
        final Category category = gateway.findById(id)
                                      .orElseThrow(() -> new NotFoundException("Category %s not found", id));
        
        category.update(
            input.getName(), 
            input.getDescription(), 
            input.getIsActive()
        );

        gateway.update(category);
    }
}