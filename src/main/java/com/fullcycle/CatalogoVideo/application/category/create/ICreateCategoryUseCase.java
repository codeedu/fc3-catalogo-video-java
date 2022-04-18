package com.fullcycle.CatalogoVideo.application.category.create;

import com.fullcycle.CatalogoVideo.application.category.common.CategoryOutputData;

public interface ICreateCategoryUseCase {
    CategoryOutputData execute(CreateCategoryInputData input);
}
