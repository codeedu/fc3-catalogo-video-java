package com.fullcycle.CatalogoVideo.application.category.create;

import com.fullcycle.CatalogoVideo.application.category.common.CategoryOutputData;
import com.fullcycle.CatalogoVideo.domain.category.Category;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateCategoryUseCase implements ICreateCategoryUseCase {

    private final ICategoryGateway gateway;

    @Override
    public CategoryOutputData execute(final CreateCategoryInputData input) {
        final var created = gateway.create(toDomain(input));
        return CategoryOutputData.fromDomain(created);
    }
    
    private Category toDomain(final CreateCategoryInputData input) {
        return new Category(
            input.getName(),
            input.getDescription(),
            input.getIsActive()
        );
    }
}
