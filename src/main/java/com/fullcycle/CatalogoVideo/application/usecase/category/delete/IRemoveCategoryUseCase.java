package com.fullcycle.CatalogoVideo.application.usecase.category.delete;

import java.util.UUID;

public interface IRemoveCategoryUseCase {
    void execute(UUID id);
}
