package com.fullcycle.CatalogoVideo.api.category;

import java.util.List;
import java.util.UUID;

import com.fullcycle.CatalogoVideo.application.category.common.CategoryOutputData;
import com.fullcycle.CatalogoVideo.application.category.create.CreateCategoryInputData;
import com.fullcycle.CatalogoVideo.application.category.create.ICreateCategoryUseCase;
import com.fullcycle.CatalogoVideo.application.category.delete.IDeleteCategoryUseCase;
import com.fullcycle.CatalogoVideo.application.category.findall.IFindAllCategoryUseCase;
import com.fullcycle.CatalogoVideo.application.category.get.IFindByIdCategoryUseCase;
import com.fullcycle.CatalogoVideo.application.category.update.IUpdateCategoryUseCase;
import com.fullcycle.CatalogoVideo.application.category.update.UpdateCategoryInputData;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CategoryEndpoint implements ICategoryEndpoint {

    private ICreateCategoryUseCase createUseCase;
    private IFindAllCategoryUseCase findAllUseCase;
    private IFindByIdCategoryUseCase findByIdUseCase;
    private IDeleteCategoryUseCase removeUseCase;
    private IUpdateCategoryUseCase updateUseCase;

    @Override
    public CategoryOutputData create(CreateCategoryInputData input) {
        var category = createUseCase.execute(input);
        return category;
    }

    @Override
    public List<CategoryOutputData> findAll() {
        return findAllUseCase.execute();
    }

    @Override
    public CategoryOutputData findById(UUID id) throws Exception {
        return findByIdUseCase.execute(id);
    }

    @Override
    public void removeById(UUID id) {
        removeUseCase.execute(id);
    }

    @Override
    public void update(UUID id, UpdateCategoryInputData input) {
        updateUseCase.execute(id, input);        
    }
}
