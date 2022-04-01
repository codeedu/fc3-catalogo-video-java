package com.fullcycle.CatalogoVideo.infrastructure.mysql;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fullcycle.CatalogoVideo.domain.entity.Category;
import com.fullcycle.CatalogoVideo.domain.repository.ICategoryRepository;
import com.fullcycle.CatalogoVideo.infrastructure.data.SpringDataCategoryRepository;
import com.fullcycle.CatalogoVideo.infrastructure.persistence.CategoryPersistence;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class MySQLCategoryRepositoryImpl implements ICategoryRepository {

    private SpringDataCategoryRepository repository;

    @Override
    public List<Category> findAll() {
        return repository.findAll()
                         .parallelStream()
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
