package com.fullcycle.CatalogoVideo.application.usecase.category.create;

import com.fullcycle.CatalogoVideo.application.usecase.category.common.CategoryOutputData;

public interface ICreateCategoryUseCase {
    CategoryOutputData execute(CreateCategoryInputData input);
}
