package com.fullcycle.CatalogoVideo.application.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import com.fullcycle.CatalogoVideo.application.category.common.CategoryOutputData;
import com.fullcycle.CatalogoVideo.application.category.get.FindByIdCategoryUseCase;
import com.fullcycle.CatalogoVideo.application.exception.NotFoundException;
import com.fullcycle.CatalogoVideo.domain.category.Category;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway;
import com.fullcycle.CatalogoVideo.runners.UnitTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class FindByIdCategoryUseCaseTests extends UnitTest {

    @InjectMocks
    private FindByIdCategoryUseCase useCase; 

    @Mock
    private ICategoryGateway repository;

    @BeforeEach
    void initUseCase() {
        Mockito.reset(repository);
    }

    @Test
    public void executeReturnsFindById() {
        Category category = new Category(
            "Action",
            "Action Description",
            true
        );

        Optional<Category> opCategory = Optional.of(category);

        when(repository.findById(category.getId()))
            .thenReturn(opCategory);
        
        CategoryOutputData actual = useCase.execute(category.getId());
        repository.findById(category.getId());
        
        assertThat(category).isNotNull();
        assertThat(actual).isNotNull();
    }

    @Test
    public void throwNotFoundExceptionWhenIdIsWrong() {
        assertThrows(NotFoundException.class, () -> useCase.execute(UUID.fromString("fe8a03d7-6495-4231-9843-8ee2f5282622")));
    }    
}
