package com.fullcycle.CatalogoVideo.application.category.findall;

import java.util.List;

import com.fullcycle.CatalogoVideo.application.category.common.CategoryOutputData;

import lombok.Builder;
import lombok.Value;

public interface IFindAllCategoryUseCase {
    
    List<CategoryOutputData> execute(Input input);

    @Builder
    @Value
    class Input {
        public String search;
    }
}
