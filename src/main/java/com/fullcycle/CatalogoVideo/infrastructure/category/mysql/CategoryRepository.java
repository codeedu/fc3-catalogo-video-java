package com.fullcycle.CatalogoVideo.infrastructure.category.mysql;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryPersistence, UUID> {
    
  Page<CategoryPersistence> findAll(Specification<CategoryPersistence> spec, Pageable pageable);
}
