package com.fullcycle.CatalogoVideo.infrastructure.category.mysql;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryPersistence, UUID> {
    
}
