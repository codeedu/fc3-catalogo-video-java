package com.fullcycle.CatalogoVideo.application.category.findall;

import java.util.List;

import com.fullcycle.CatalogoVideo.application.category.common.CategoryOutputData;

public interface IFindAllCategoryUseCase {
    List<CategoryOutputData> execute();
}
