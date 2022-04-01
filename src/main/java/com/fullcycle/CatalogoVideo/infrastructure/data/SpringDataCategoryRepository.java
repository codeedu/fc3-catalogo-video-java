package com.fullcycle.CatalogoVideo.infrastructure.data;

import java.util.UUID;

import com.fullcycle.CatalogoVideo.infrastructure.persistence.CategoryPersistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCategoryRepository extends JpaRepository<CategoryPersistence, UUID> {
    
}
