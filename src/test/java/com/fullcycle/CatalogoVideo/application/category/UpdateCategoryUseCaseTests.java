package com.fullcycle.CatalogoVideo.application.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import com.fullcycle.CatalogoVideo.application.category.update.UpdateCategoryInputData;
import com.fullcycle.CatalogoVideo.application.category.update.UpdateCategoryUseCase;
import com.fullcycle.CatalogoVideo.application.exception.NotFoundException;
import com.fullcycle.CatalogoVideo.domain.category.Category;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway;
import com.fullcycle.CatalogoVideo.runners.UnitTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class UpdateCategoryUseCaseTests extends UnitTest {

    @InjectMocks
    private UpdateCategoryUseCase useCase; 

    @Mock
    private ICategoryGateway repository;

    @BeforeEach
    void initUseCase() {
        Mockito.reset(repository);
    }

    @Test
    public void executeReturnsUpdatedCategoryName() throws Exception {
        Category category = Category.newCategory(
            "Action",
            "Action Description",
            true
        );

        Category expected = Category.newCategory(
            "Action 2",
            "Description",
            true
        );

        Optional<Category> opCategory = Optional.of(category);

        when(repository.findById(category.getId()))
            .thenReturn(opCategory);
        
        UpdateCategoryInputData input = new UpdateCategoryInputData();
        input.setName("Action 2");
        input.setDescription("Description");
        input.setIsActive(category.getIsActive());

        category.update(
            input.getName(),
            input.getDescription(),
            input.getIsActive()
        );

        doNothing()
            .when(repository)
            .update(category);

        useCase.execute(category.getId(), input);
        
        assertThat(category).isNotNull();
        assertThat(expected).isNotNull();
        assertThat(category.getName()).isEqualTo(expected.getName());
    }

    @Test
    public void executeReturnsUpdatedCategory() throws Exception {
        Category category = Category.newCategory(
            "Action",
            "Action Description",
            true
        );

        Category expected = Category.newCategory(
            "Action 2",
            "Description 2",
            false
        );

        Optional<Category> opCategory = Optional.of(category);

        when(repository.findById(category.getId()))
            .thenReturn(opCategory);
        
        UpdateCategoryInputData input = new UpdateCategoryInputData();
        input.setName("Action 2");
        input.setDescription("Description 2");
        input.setIsActive(false);

        category.update(
            input.getName(),
            input.getDescription(),
            input.getIsActive()
        );

        doNothing()
            .when(repository)
            .update(category);

        useCase.execute(category.getId(), input);
        
        assertThat(category).isNotNull();
        assertThat(expected).isNotNull();
        assertThat(category.getName()).isEqualTo(expected.getName());
        assertThat(category.getDescription()).isEqualTo(expected.getDescription());
        assertFalse(category.getIsActive());
    }    

    @Test
    public void throwNotFoundExceptionWhenIdIsWrong() {
        Category category = Category.newCategory(
            "Action",
            "Action Description",
            true
        );  

        UpdateCategoryInputData input = new UpdateCategoryInputData();
        input.setName("Action 2");
        input.setDescription("Description");
        input.setIsActive(category.getIsActive());          
        
        assertThrows(NotFoundException.class, () -> useCase.execute(UUID.fromString("fe8a03d7-6495-4231-9843-8ee2f5282622"), input));
    }        
}
