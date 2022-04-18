package com.fullcycle.CatalogoVideo.application.category.findall;

import com.fullcycle.CatalogoVideo.application.category.common.CategoryOutputData;
import com.fullcycle.CatalogoVideo.domain.common.Pagination;

import lombok.Builder;
import lombok.Value;

public interface IFindAllCategoryUseCase {
    
    Pagination<CategoryOutputData> execute(Input input);

    @Builder
    @Value
    class Input {
        private static final String DEFAULT_SEARCH = "";
        private static final int DEFAULT_PER_PAGE = 15;
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
            this.perPage = perPage != 0 ? perPage : DEFAULT_PER_PAGE;
            this.sort = sort != null ? sort : DEFAULT_SORT;
            this.direction = dir != null ? dir : DEFAULT_DIRECTION;
        }
    }
}
