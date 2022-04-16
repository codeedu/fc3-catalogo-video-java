package com.fullcycle.CatalogoVideo.infrastructure.category.mysql;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryPersistence, UUID> {
    
  List<CategoryPersistence> findAll(Specification<CategoryPersistence> spec);
}
