package com.fullcycle.CatalogoVideo.infrastructure.category.mysql;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import com.fullcycle.CatalogoVideo.domain.category.Category;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class MySQLCategoryGateway implements ICategoryGateway {

    private CategoryRepository repository;

    @Override
    public List<Category> findAll() {
        return repository.findAll()
                         .stream()
                         .map(CategoryPersistence::fromThis)
                         .collect(Collectors.toList());
    }

    @Override
    public Category create(Category category) {
        final CategoryPersistence entity = CategoryPersistence.from(category);
        return repository.save(entity)
                         .fromThis();
    }

    @Override
    public Optional<Category> findById(UUID id) {
        return repository.findById(id)
                         .map(CategoryPersistence::fromThis);
    }

    @Override
    public void remove(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Category category) {
        repository.save(CategoryPersistence.from(category));
    }
    
}
