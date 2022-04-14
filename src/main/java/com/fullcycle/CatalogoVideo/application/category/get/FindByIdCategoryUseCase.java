package com.fullcycle.CatalogoVideo.application.category.get;

import java.util.UUID;

import com.fullcycle.CatalogoVideo.application.category.common.CategoryOutputData;
import com.fullcycle.CatalogoVideo.application.exception.NotFoundException;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindByIdCategoryUseCase implements IFindByIdCategoryUseCase {

    private final ICategoryGateway gateway;

    @Override
    public CategoryOutputData execute(final UUID id) {
        return gateway.findById(id)
                         .map(CategoryOutputData::fromDomain)
                         .orElseThrow(() -> new NotFoundException("Category %s not found", id));
    }
    
}
