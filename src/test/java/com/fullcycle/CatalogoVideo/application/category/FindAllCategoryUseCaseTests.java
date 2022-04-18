package com.fullcycle.CatalogoVideo.application.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import com.fullcycle.CatalogoVideo.application.category.findall.FindAllCategoryUseCase;
import com.fullcycle.CatalogoVideo.application.category.findall.IFindAllCategoryUseCase;
import com.fullcycle.CatalogoVideo.application.category.findall.IFindAllCategoryUseCase.Input;
import com.fullcycle.CatalogoVideo.domain.category.Category;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway.FindAllInput;
import com.fullcycle.CatalogoVideo.domain.common.Pagination;
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
    private ICategoryGateway gateway;

    @BeforeEach
    void initUseCase() {
        Mockito.reset(gateway);
    }

    @Test
    public void executeReturnsFindAllCategory() {
        final var expectedDefaultSearch = "";
        final var expectedDefaultPage = 0;
        final var expectedDefaultPerPage = 15;
        final var expectedDefaultSort = "name";
        final var expectedDefaultDir = "asc";
        final var expectedElementsCount = 1;

        final var pageResult = Pagination.<Category>builder()
            .currentPage(expectedDefaultPage)
            .perPage(expectedDefaultPerPage)
            .total(expectedElementsCount)
            .items(List.of(
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
            ))
            .build();

        final FindAllInput expectedGatewayInput = ICategoryGateway.FindAllInput.builder()
            .search(expectedDefaultSearch)
            .page(expectedDefaultPage)
            .perPage(expectedDefaultPerPage)
            .sort(expectedDefaultSort)
            .direction(expectedDefaultDir)
            .build();

        when(gateway.findAll(expectedGatewayInput))
            .thenReturn(pageResult);

        final var actualResult = useCase.execute(IFindAllCategoryUseCase.Input.builder().build());
        
        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getItems()).hasSize(3);
        assertThat(actualResult.getCurrentPage()).isEqualTo(expectedDefaultPage);
        assertThat(actualResult.getPerPage()).isEqualTo(expectedDefaultPerPage);
        assertThat(actualResult.getTotal()).isEqualTo(expectedElementsCount);
    }

    @Test
    public void executeReturnsFindAllCategoryAndListSizeIsZero() {
        final var expectedSearch = "act";
        final var expectedPage = 2;
        final var expectedPerPage = 20;
        final var expectedSort = "description";
        final var expectedDir = "desc";
        final var expectedElementsCount = 1;

        final FindAllInput expectedGatewayInput = ICategoryGateway.FindAllInput.builder()
            .search(expectedSearch)
            .page(expectedPage)
            .perPage(expectedPerPage)
            .sort(expectedSort)
            .direction(expectedDir)
            .build();

        final var pageResult = Pagination.<Category>builder()
            .currentPage(expectedPage)
            .perPage(expectedPerPage)
            .total(expectedElementsCount)
            .items(List.of())
            .build();
            
        when(gateway.findAll(eq(expectedGatewayInput))).thenReturn(pageResult);

        final Input useCaseInput = IFindAllCategoryUseCase.Input.builder()
            .search(expectedSearch)
            .page(expectedPage)
            .perPage(expectedPerPage)
            .sort(expectedSort)
            .direction(expectedDir)
            .build();
        
        final var actualResult = useCase.execute(useCaseInput);
        
        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getItems()).hasSize(0);
        assertThat(actualResult.getCurrentPage()).isEqualTo(expectedPage);
        assertThat(actualResult.getPerPage()).isEqualTo(expectedPerPage);
        assertThat(actualResult.getTotal()).isEqualTo(expectedElementsCount);
        
        verify(gateway, times(1)).findAll(eq(expectedGatewayInput));
    }
}
