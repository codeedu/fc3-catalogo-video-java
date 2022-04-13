package com.fullcycle.CatalogoVideo.domain.category.gateways;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.fullcycle.CatalogoVideo.domain.category.Category;

public interface ICategoryGateway {
    List<Category> findAll();
    Category create(Category category);
    Optional<Category> findById(UUID id);
    void remove(UUID id);
    void update(Category category);
}
