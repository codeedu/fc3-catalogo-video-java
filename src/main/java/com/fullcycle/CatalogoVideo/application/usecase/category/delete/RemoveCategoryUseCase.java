package com.fullcycle.CatalogoVideo.application.usecase.category.delete;

import java.util.UUID;

import com.fullcycle.CatalogoVideo.domain.repository.ICategoryRepository;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RemoveCategoryUseCase implements IRemoveCategoryUseCase {

    private ICategoryRepository repository;

    @Override
    public void execute(UUID id) {
        repository.remove(id);
    }
    
}
