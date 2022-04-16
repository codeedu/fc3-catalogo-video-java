package com.fullcycle.CatalogoVideo.infrastructure.category.mysql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fullcycle.CatalogoVideo.domain.category.Category;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway.FindAllInput;
import com.fullcycle.CatalogoVideo.runners.UnitTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class MySQLCategoryGatewayImplTests extends UnitTest {

    @InjectMocks
    private MySQLCategoryGateway repository;

    @Mock
    private CategoryRepository springDataRepository;

    @BeforeEach
    void beforeEach() {
        Mockito.reset(springDataRepository);
    }

    @Test
    public void saveCategory() {
        Category expected = Category.newCategory(
            "Action",
            "Action Description",
            true
        );
        Category input = Category.newCategory(
            "Action",
            "Action Description",
            true            
        );

        doReturn(CategoryPersistence.from(expected))
            .when(springDataRepository)
            .save(any(CategoryPersistence.class));
        
        Category actual = repository.create(input);

        assertThat(actual).isNotNull();
        assertThat(actual.getName()).isEqualTo(expected.getName());
        assertThat(actual).hasFieldOrPropertyWithValue("name", "Action");
        assertTrue(actual.getIsActive());
    }

    @Test
    public void findAllCategories() {
        Category entity1 = Category.newCategory(
            "Action",
            "Action Description",
            true
        );
        Category entity2 = Category.newCategory(
            "Horror",
            "Horror Description",
            true            
        );        

        List<CategoryPersistence> expected = new ArrayList<CategoryPersistence>();
        expected.add(CategoryPersistence.from(entity1));
        expected.add(CategoryPersistence.from(entity2));

        doReturn(expected)
            .when(springDataRepository).findAll();

        final FindAllInput findAllInput = ICategoryGateway.FindAllInput.builder()
        .build();

        List<Category> actual = repository.findAll(findAllInput);

        assertThat(actual).isNotNull();
        assertThat(actual).isNotEmpty();
        assertThat(actual).hasSize(2);
    }

    @Test
    public void findByIdCategory() {
        Category entity = Category.newCategory(
            "Action",
            "Action Description",
            true
        );        

        CategoryPersistence input = CategoryPersistence.from(entity);

        doReturn(Optional.of(input))
            .when(springDataRepository)
            .findById(entity.getId());

        Optional<Category> actual = repository.findById(entity.getId());

        assertThat(actual.isPresent()).isTrue();
        assertThat(actual).isNotNull();
    }

    @Test
    public void deleteCategory() {
        Category entity = Category.newCategory(
            "Action",
            "Action Description",
            true
        );    

        doNothing()
            .when(springDataRepository)
            .deleteById(entity.getId());

        repository.remove(entity.getId());  
        
        verify(springDataRepository, times(1)).deleteById(entity.getId());
    }

    @Test
    public void updateCategory() {
        Category expected = Category.newCategory(
            "Action",
            "Action Description",
            true
        );
        Category input = Category.newCategory(
            "Action",
            "Action Description",
            true            
        );

        doReturn(CategoryPersistence.from(expected))
            .when(springDataRepository)
            .save(any(CategoryPersistence.class));
        
        Category toUpdate = repository.create(input);

        toUpdate.update("Horror", toUpdate.getDescription(), false);

        doReturn(CategoryPersistence.from(toUpdate))
            .when(springDataRepository)
            .save(any(CategoryPersistence.class));

        repository.update(toUpdate);

        assertThat(toUpdate).isNotNull();
        assertThat(toUpdate).hasFieldOrPropertyWithValue("name", "Horror");
        assertThat(toUpdate.getIsActive()).isFalse();
    }
}
