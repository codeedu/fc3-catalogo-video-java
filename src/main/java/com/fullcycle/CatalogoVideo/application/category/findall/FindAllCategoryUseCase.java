package com.fullcycle.CatalogoVideo.application.category.findall;

import java.util.List;
import java.util.stream.Collectors;

import com.fullcycle.CatalogoVideo.application.category.common.CategoryOutputData;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindAllCategoryUseCase implements IFindAllCategoryUseCase {

    private final ICategoryGateway gateway;
    
    @Override
    public List<CategoryOutputData> execute() {
        return gateway.findAll()
                .stream()
                .map(c -> CategoryOutputData.fromDomain(c))
                .collect(Collectors.toList());
    }
    
}
