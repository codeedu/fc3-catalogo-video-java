package com.fullcycle.CatalogoVideo.api.category;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
@Api(value = "Categories")
public interface ICategoryEndpoint {
    
    @PostMapping
    @ResponseStatus(code = CREATED)
    @ApiOperation("Create a new category")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created Success"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public CategoryOutputData create(@RequestBody CreateCategoryInputData input);

    @GetMapping
    @ResponseStatus(code = OK)
    @ApiOperation("Find all categories")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "List Success"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public List<CategoryOutputData> findAll();

    @GetMapping("/{id}")
    @ResponseStatus(code = OK)
    @ApiOperation("Find by id category")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Find Success"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })    
    public CategoryOutputData findById(@PathVariable UUID id) throws Exception;

    @DeleteMapping("/{id}")
    @ResponseStatus(code = NO_CONTENT)
    @ApiOperation("Remove a category by id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Removed Success"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })    
    public void removeById(@PathVariable UUID id);

    @PutMapping("/{id}")
    @ResponseStatus(code = NO_CONTENT)
    @ApiOperation("Update a category by id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Updated Success"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })    
    public void update(@PathVariable UUID id, UpdateCategoryInputData input);
}
