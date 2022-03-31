package com.fullcycle.CatalogoVideo.application.usecase.category.findall;

import java.util.List;
import java.util.stream.Collectors;

import com.fullcycle.CatalogoVideo.application.usecase.category.common.CategoryOutputData;
import com.fullcycle.CatalogoVideo.domain.repository.ICategoryRepository;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FindAllCategoryUseCase implements IFindAllCategoryUseCase {

    private ICategoryRepository repository;
    
    @Override
    public List<CategoryOutputData> execute() {
        var list = repository.findAll();
        return list.stream()
                   .map(c -> CategoryOutputData.fromDomain(c))
                   .collect(Collectors.toList());
    }
    
}
