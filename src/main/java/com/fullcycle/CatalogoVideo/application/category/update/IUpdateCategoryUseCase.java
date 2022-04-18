package com.fullcycle.CatalogoVideo.application.category.update;

import java.util.UUID;

public interface IUpdateCategoryUseCase {
    
    void execute(UUID id, UpdateCategoryInputData input);
}
