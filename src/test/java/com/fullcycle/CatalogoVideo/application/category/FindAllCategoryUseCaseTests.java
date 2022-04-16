package com.fullcycle.CatalogoVideo.application.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.fullcycle.CatalogoVideo.application.category.common.CategoryOutputData;
import com.fullcycle.CatalogoVideo.application.category.findall.FindAllCategoryUseCase;
import com.fullcycle.CatalogoVideo.application.category.findall.IFindAllCategoryUseCase;
import com.fullcycle.CatalogoVideo.application.category.findall.IFindAllCategoryUseCase.Input;
import com.fullcycle.CatalogoVideo.domain.category.Category;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway.FindAllInput;
import com.fullcycle.CatalogoVideo.runners.UnitTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class FindAllCategoryUseCaseTests extends UnitTest {

    @InjectMocks
    private FindAllCategoryUseCase useCase; 

    @Mock
    private ICategoryGateway repository;

    @BeforeEach
    void initUseCase() {
        Mockito.reset(repository);
    }

    @Test
    public void executeReturnsFindAllCategory() {
        List<Category> categories = Arrays.asList(
            Category.newCategory(
                "Action",
                "Action Description",
                true
            ),
            Category.newCategory(
                "Horror",
                "Horror Description",
                true
            ),
            Category.newCategory(
                "Suspense",
                "Suspense Description",
                true
            )
        );

        final FindAllInput gatewayInput = ICategoryGateway.FindAllInput.builder()
            .build();

        when(repository.findAll(gatewayInput)).thenReturn(categories);

        useCase.execute(
            IFindAllCategoryUseCase.Input.builder()
            .build()
        );
        
        assertThat(categories).isNotNull();
        assertThat(categories).hasSize(3);
    }

    @Test
    public void executeReturnsFindAllCategoryAndListSizeIsZero() {
        final String expectedSearch = "act";

        final FindAllInput gatewayInput = ICategoryGateway.FindAllInput.builder()
            .search(expectedSearch)
            .build();

        when(repository.findAll(eq(gatewayInput))).thenReturn(List.of());

        final Input useCaseInput = IFindAllCategoryUseCase.Input.builder()
            .search(expectedSearch)
            .build();
        
        final List<CategoryOutputData> actual = useCase.execute(useCaseInput);
        
        assertThat(actual).isNotNull();
        assertThat(actual).hasSize(0);
        
        verify(repository, times(1)).findAll(eq(gatewayInput));
    }
}
