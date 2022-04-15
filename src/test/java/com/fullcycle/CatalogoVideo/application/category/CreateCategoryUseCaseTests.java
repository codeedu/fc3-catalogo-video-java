package com.fullcycle.CatalogoVideo.application.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.fullcycle.CatalogoVideo.application.category.common.CategoryOutputData;
import com.fullcycle.CatalogoVideo.application.category.create.CreateCategoryInputData;
import com.fullcycle.CatalogoVideo.application.category.create.CreateCategoryUseCase;
import com.fullcycle.CatalogoVideo.domain.category.Category;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway;
import com.fullcycle.CatalogoVideo.runners.UnitTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class CreateCategoryUseCaseTests extends UnitTest {
    
    @InjectMocks
    private CreateCategoryUseCase useCase; 

    @Mock
    private ICategoryGateway gateway;

    @BeforeEach
    void initUseCase() {
        Mockito.reset(gateway);
    }

    @Test
    public void givenValidInput_whenSuccessfullyCreateCategory_shouldReturnOutputFulfilled() {
        final CreateCategoryInputData input = new CreateCategoryInputData(
            "Action",
            "The most watched category",
            true
        );

        when(gateway.create(any(Category.class)))
            .thenReturn(input.toDomain());

        final CategoryOutputData actual = useCase.execute(input);

        assertThat(actual.getName()).isEqualTo(input.getName());
        assertThat(actual.getDescription()).isEqualTo(input.getDescription());
        assertTrue(actual.getIsActive());
        assertNotNull(actual.getCreatedAt());
        assertNotNull(actual.getUpdatedAt());
        assertNull(actual.getDeletedAt());
    }  
    
    @Test
    public void givenValidInputWithoutDescription_whenSuccessfullyCreateCategory_shouldReturnOutputFulfilled() {
        final CreateCategoryInputData input = new CreateCategoryInputData(
            "Action",
            null,
            true
        );

        when(gateway.create(any(Category.class)))
            .thenReturn(input.toDomain());
        
        final CategoryOutputData actual = useCase.execute(input);

        assertThat(actual.getName()).isEqualTo(input.getName());
        assertThat(actual.getDescription()).isNull();
        assertTrue(actual.getIsActive());
        assertNotNull(actual.getCreatedAt());
        assertNotNull(actual.getUpdatedAt());
        assertNull(actual.getDeletedAt());
    } 

    @Test
    public void givenValidInputInactivated_whenSuccessfullyCreateCategory_shouldReturnOutputFulfilled() {
        final CreateCategoryInputData input = new CreateCategoryInputData(
            "Action",
            "The most watched category",
            false
        );

        when(gateway.create(any(Category.class)))
            .thenReturn(input.toDomain());
        
        final CategoryOutputData actual = useCase.execute(input);

        assertThat(actual.getName()).isEqualTo(input.getName());
        assertThat(actual.getDescription()).isEqualTo(input.getDescription());
        assertFalse(actual.getIsActive());
        assertNotNull(actual.getCreatedAt());
        assertNotNull(actual.getUpdatedAt());
        assertNull(actual.getDeletedAt());
    }     
}