package com.fullcycle.CatalogoVideo.infrastructure.category.mysql;

import static org.springframework.data.jpa.domain.Specification.where;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fullcycle.CatalogoVideo.domain.category.Category;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class MySQLCategoryGateway implements ICategoryGateway {

    private final CategoryRepository repository;

    @Override
    public List<Category> findAll(final FindAllInput input) {
        Specification<CategoryPersistence> where = null;

        if (StringUtils.hasLength(input.search)) {
            final var searchLike = "%" + input.search + "%";
    
            final Specification<CategoryPersistence> nameLike = 
                (root, query, cb) -> cb.like(root.get("name"), searchLike);
            
            final Specification<CategoryPersistence> descriptionLike = 
                (root, query, cb) -> cb.like(root.get("description"), searchLike);

            where = where(nameLike.or(descriptionLike));
        }

        return repository.findAll(where)
                         .stream()
                         .map(CategoryPersistence::fromThis)
                         .collect(Collectors.toList());
    }

    @Override
    @Transactional
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
    @Transactional
    public void remove(UUID id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Category category) {
        repository.saveAndFlush(CategoryPersistence.from(category));
    }
}