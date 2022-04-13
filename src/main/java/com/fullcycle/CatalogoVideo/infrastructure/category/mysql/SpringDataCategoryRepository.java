package com.fullcycle.CatalogoVideo.infrastructure.category.mysql;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCategoryRepository extends JpaRepository<CategoryPersistence, UUID> {
    
}
