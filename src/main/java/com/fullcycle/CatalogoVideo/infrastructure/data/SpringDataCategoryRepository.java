package com.fullcycle.CatalogoVideo.infrastructure.data;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface SpringDataCategoryRepository extends CrudRepository<null, UUID> {
    
}
