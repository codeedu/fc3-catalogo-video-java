package com.fullcycle.CatalogoVideo.application.category.delete;

import java.util.UUID;

import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteCategoryUseCase implements IDeleteCategoryUseCase {

    private final ICategoryGateway gateway;

    @Override
    public void execute(final UUID id) {
        gateway.remove(id);
    }
}