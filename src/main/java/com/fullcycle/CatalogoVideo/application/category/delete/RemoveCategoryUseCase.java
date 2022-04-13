package com.fullcycle.CatalogoVideo.application.category.delete;

import java.util.UUID;

import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RemoveCategoryUseCase implements IRemoveCategoryUseCase {

    private ICategoryGateway repository;

    @Override
    public void execute(UUID id) {
        repository.remove(id);
    }
    
}
