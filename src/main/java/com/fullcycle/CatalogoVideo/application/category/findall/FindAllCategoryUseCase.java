package com.fullcycle.CatalogoVideo.application.category.findall;

import java.util.List;
import java.util.stream.Collectors;

import com.fullcycle.CatalogoVideo.application.category.common.CategoryOutputData;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FindAllCategoryUseCase implements IFindAllCategoryUseCase {

    private ICategoryGateway repository;
    
    @Override
    public List<CategoryOutputData> execute() {
        var list = repository.findAll();
        return list.stream()
                   .map(c -> CategoryOutputData.fromDomain(c))
                   .collect(Collectors.toList());
    }
    
}
