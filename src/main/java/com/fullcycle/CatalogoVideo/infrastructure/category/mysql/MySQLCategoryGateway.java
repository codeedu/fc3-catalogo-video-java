package com.fullcycle.CatalogoVideo.infrastructure.category.mysql;

import static com.fullcycle.CatalogoVideo.infrastructure.common.Specifications.like;
import static org.springframework.data.jpa.domain.Specification.where;

import java.util.Optional;
import java.util.UUID;

import com.fullcycle.CatalogoVideo.domain.category.Category;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway;
import com.fullcycle.CatalogoVideo.domain.common.Pagination;
import com.fullcycle.CatalogoVideo.infrastructure.common.Specifications;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
    public Pagination<Category> findAll(final FindAllInput input) {
        Specification<CategoryPersistence> where = null;

        if (StringUtils.hasLength(input.search)) {
            where = where(
                Specifications.<CategoryPersistence>like("name", input.search)
                    .or(like("description", input.search))
            );
        }

        final PageRequest page = PageRequest.of(
            input.page,
            input.perPage,
            Sort.by(Direction.fromString(input.direction), input.sort)
        );

        final var queryResult = repository.findAll(where, page);

        return Pagination.<Category>builder()
            .currentPage(queryResult.getNumber())
            .perPage(queryResult.getSize())
            .total(queryResult.getTotalElements())
            .items(queryResult.map(CategoryPersistence::fromThis).toList())
            .build();
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