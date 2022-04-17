package com.fullcycle.CatalogoVideo.domain.category.gateways;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.fullcycle.CatalogoVideo.domain.category.Category;

import lombok.Builder;
import lombok.Value;

public interface ICategoryGateway {
    
    List<Category> findAll(FindAllInput input);
    Category create(Category category);
    Optional<Category> findById(UUID id);
    void remove(UUID id);
    void update(Category category);

    @Builder
    @Value
    class FindAllInput {
        public String search;
        public int page;
        public int perPage;
        public String sort;
        public String direction;
    }
}
