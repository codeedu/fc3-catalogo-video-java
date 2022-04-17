package com.fullcycle.CatalogoVideo.application.category.findall;

import com.fullcycle.CatalogoVideo.application.category.common.CategoryOutputData;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway.FindAllInput;
import com.fullcycle.CatalogoVideo.domain.common.Pagination;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindAllCategoryUseCase implements IFindAllCategoryUseCase {

    private final ICategoryGateway gateway;
    
    @Override
    public Pagination<CategoryOutputData> execute(final Input input) {
        final FindAllInput gatewayInput = ICategoryGateway.FindAllInput.builder()
            .search(input.search)
            .page(input.page)
            .perPage(input.perPage)
            .sort(input.sort)
            .direction(input.direction)
            .build();

        return gateway.findAll(gatewayInput)
            .map(CategoryOutputData::fromDomain);
    }
}