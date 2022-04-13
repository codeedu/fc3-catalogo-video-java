package com.fullcycle.CatalogoVideo.application.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.fullcycle.CatalogoVideo.application.category.delete.RemoveCategoryUseCase;
import com.fullcycle.CatalogoVideo.domain.category.Category;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway;
import com.fullcycle.CatalogoVideo.runners.UnitTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class RemoveCategoryUseCaseTests extends UnitTest {

    @InjectMocks
    private RemoveCategoryUseCase useCase; 

    @Mock
    private ICategoryGateway repository;

    @BeforeEach
    void initUseCase() {
        Mockito.reset(repository);
    }

    @Test
    public void executeReturnsRemove() throws Exception {
        Category category = new Category(
            "Action",
            "Action Description",
            true
        );

        doNothing()
            .when(repository)
            .remove(category.getId());

        useCase.execute(category.getId());
        repository.remove(category.getId());
        
        assertThat(category).isNotNull();
        verify(repository, times(2)).remove(category.getId());
    }
}
