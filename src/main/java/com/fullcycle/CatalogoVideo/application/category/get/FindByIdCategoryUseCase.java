package com.fullcycle.CatalogoVideo.application.category.get;

import java.util.UUID;

import com.fullcycle.CatalogoVideo.application.category.common.CategoryOutputData;
import com.fullcycle.CatalogoVideo.application.exception.NotFoundException;
import com.fullcycle.CatalogoVideo.domain.category.repository.ICategoryRepository;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FindByIdCategoryUseCase implements IFindByIdCategoryUseCase {

    private ICategoryRepository repository;

    @Override
    public CategoryOutputData execute(UUID id) {
        return repository.findById(id)
                         .map(CategoryOutputData::fromDomain)
                         .orElseThrow(() -> new NotFoundException("Category %s not found", id));
    }
    
}
