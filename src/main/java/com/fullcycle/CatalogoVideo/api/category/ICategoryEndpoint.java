package com.fullcycle.CatalogoVideo.api.category;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import com.fullcycle.CatalogoVideo.application.usecase.category.common.CategoryOutputData;
import com.fullcycle.CatalogoVideo.application.usecase.category.create.CreateCategoryInputData;
import com.fullcycle.CatalogoVideo.application.usecase.category.update.UpdateCategoryInputData;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/categories")
public interface ICategoryEndpoint {
    
    @PostMapping
    @ResponseStatus(code = CREATED)
    public CategoryOutputData create(@RequestBody CreateCategoryInputData input);

    @GetMapping
    @ResponseStatus(code = OK)
    public List<CategoryOutputData> findAll();

    @GetMapping("/{id}")
    @ResponseStatus(code = OK)
    public CategoryOutputData findById(@PathVariable UUID id) throws Exception;

    @DeleteMapping("/{id}")
    @ResponseStatus(code = NO_CONTENT)
    public void removeById(@PathVariable UUID id);

    @PutMapping("/{id}")
    @ResponseStatus(code = NO_CONTENT)
    public void update(@PathVariable UUID id, UpdateCategoryInputData input);
}
