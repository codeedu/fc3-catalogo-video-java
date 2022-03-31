package com.fullcycle.CatalogoVideo.application.usecase.category.get;

import java.util.UUID;

import com.fullcycle.CatalogoVideo.application.usecase.category.common.CategoryOutputData;

public interface IFindByIdCategoryUseCase {

    CategoryOutputData execute(UUID id) throws Exception;
}
