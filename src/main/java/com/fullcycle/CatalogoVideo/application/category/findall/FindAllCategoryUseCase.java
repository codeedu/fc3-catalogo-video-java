package com.fullcycle.CatalogoVideo.application.category.findall;

import java.util.List;
import java.util.stream.Collectors;

import com.fullcycle.CatalogoVideo.application.category.common.CategoryOutputData;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway.FindAllInput;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindAllCategoryUseCase implements IFindAllCategoryUseCase {

    private final ICategoryGateway gateway;
    
    @Override
    public List<CategoryOutputData> execute(final Input input) {
        final FindAllInput gatewayInput = ICategoryGateway.FindAllInput.builder()
            .search(input.search)
            .page(input.page)
            .perPage(input.perPage)
            .sort(input.sort)
            .direction(input.direction)
            .build();

        return gateway.findAll(gatewayInput)
                .stream()
                .map(c -> CategoryOutputData.fromDomain(c))
                .collect(Collectors.toList());
    }
    
}
