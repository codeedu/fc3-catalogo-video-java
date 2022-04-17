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
        private static final String DEFAULT_SEARCH = "";
        private static final String DEFAULT_SORT = "name";
        private static final String DEFAULT_DIRECTION = "asc";

        public String search;
        public int page;
        public int perPage;
        public String sort;
        public String direction;

        public Input(
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String dir
        ) {
            this.search = search != null ? search : DEFAULT_SEARCH;
            this.page = page;
            this.perPage = perPage;
            this.sort = sort != null ? sort : DEFAULT_SORT;
            this.direction = dir != null ? dir : DEFAULT_DIRECTION;
        }
    }
}
