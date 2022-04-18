package com.fullcycle.CatalogoVideo.infrastructure.category.mysql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import com.fullcycle.CatalogoVideo.domain.category.Category;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway.FindAllInput;
import com.fullcycle.CatalogoVideo.runners.IntegrationTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@Import(MySQLCategoryGateway.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class MySQLCategoryGatewayImplTests extends IntegrationTest {

    @Autowired
    private MySQLCategoryGateway gateway;

    @Autowired
    private CategoryRepository repository;

    @BeforeEach
    void beforeEach() {
        repository.deleteAll();
        repository.flush();
    }

    @Test
    public void saveCategory() {
        final String expectedName = "Action";
        final String expectedDescription = "Action Description";
        final Boolean expectedIsActive = true;

        final Category input = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        
        final Category actual = gateway.create(input);

        assertThat(actual).isNotNull();
        assertNotNull(actual.getId());
        assertNotNull(actual.getCreatedAt());
        assertNotNull(actual.getUpdatedAt());
        assertThat(actual.getName()).isEqualTo(expectedName);
        assertThat(actual.getDescription()).isEqualTo(expectedDescription);
        assertTrue(actual.getIsActive());
        assertNull(actual.getDeletedAt());
    }

    @Test
    public void findAllCategories() {
        final var expectedItemSize = 2;
        final var expectedPage = 0;
        final var expectedPerPage = 15;
        final var expectedElementsCount = 2;

        repository.save(CategoryPersistence.from(Category.newCategory(
            "Action",
            "Action Description",
            true
        )));
        
        repository.save(CategoryPersistence.from(Category.newCategory(
            "Horror",
            "Horror Description",
            true            
        )));

        assertEquals(repository.count(), 2);

        final var findAllInput = ICategoryGateway.FindAllInput.builder()
            .page(expectedPage)
            .perPage(expectedPerPage)
            .sort("name")
            .direction("asc")
            .build();

        final var actual = gateway.findAll(findAllInput);

        assertThat(actual).isNotNull();
        assertThat(actual.getItems()).hasSize(expectedItemSize);
        assertThat(actual.getCurrentPage()).isEqualTo(expectedPage);
        assertThat(actual.getPerPage()).isEqualTo(expectedPerPage);
        assertThat(actual.getTotal()).isEqualTo(expectedElementsCount);
    }

    @Test
    public void givenNamAsSearch_whenMatchesWithActionName_shouldReturnActionCategory() {
        final var expectedItemSize = 1;
        final var expectedPage = 0;
        final var expectedPerPage = 15;
        final var expectedElementsCount = 1;

        final var expectedAction = repository.save(CategoryPersistence.from(Category.newCategory(
            "Action Name",
            "Action Description",
            true
        )));
        
        repository.save(CategoryPersistence.from(Category.newCategory(
            "Horror",
            "Horror Description",
            true            
        )));

        assertEquals(repository.count(), 2);

        final FindAllInput findAllInput = ICategoryGateway.FindAllInput.builder()
            .search("nam")
            .page(expectedPage)
            .perPage(expectedPerPage)
            .sort("name")
            .direction("asc")
            .build();

        final var actual = gateway.findAll(findAllInput);

        assertThat(actual).isNotNull();
        assertThat(actual.getItems()).hasSize(expectedItemSize);
        assertThat(actual.getItems().get(0).getId()).isEqualTo(expectedAction.getId());
        assertThat(actual.getCurrentPage()).isEqualTo(expectedPage);
        assertThat(actual.getPerPage()).isEqualTo(expectedPerPage);
        assertThat(actual.getTotal()).isEqualTo(expectedElementsCount);
    }

    @Test
    public void givenDescriptionAsSearch_whenMatchesWithActionDescription_shouldReturnActionCategory() {
        final var expectedItemSize = 1;
        final var expectedPage = 0;
        final var expectedPerPage = 15;
        final var expectedElementsCount = 1;

        final var expectedAction = repository.save(CategoryPersistence.from(Category.newCategory(
            "Action Name",
            "Action Description",
            true
        )));
        
        repository.save(CategoryPersistence.from(Category.newCategory(
            "Horror",
            "Horror",
            true            
        )));

        assertEquals(repository.count(), 2);

        final FindAllInput findAllInput = ICategoryGateway.FindAllInput.builder()
            .search("desc")
            .page(expectedPage)
            .perPage(expectedPerPage)
            .sort("name")
            .direction("asc")
            .build();

        final var actual = gateway.findAll(findAllInput);

        assertThat(actual).isNotNull();
        assertThat(actual.getItems()).hasSize(expectedItemSize);
        assertThat(actual.getItems().get(0).getId()).isEqualTo(expectedAction.getId());
        assertThat(actual.getCurrentPage()).isEqualTo(expectedPage);
        assertThat(actual.getPerPage()).isEqualTo(expectedPerPage);
        assertThat(actual.getTotal()).isEqualTo(expectedElementsCount);
    }

    @Test
    public void findByIdCategory() {
        final var expectedAction = repository.save(CategoryPersistence.from(Category.newCategory(
            "Action Name",
            "Action Description",
            true
        )));

        final Optional<Category> actual = gateway.findById(expectedAction.getId());

        assertThat(actual.isPresent()).isTrue();

        final Category category = actual.get();
        
        assertEquals(expectedAction.getId(), category.getId());
    }

    @Test
    public void deleteCategory() {
        final var expectedAction = repository.save(CategoryPersistence.from(Category.newCategory(
            "Action Name",
            "Action Description",
            true
        ))); 

        assertEquals(1, repository.count());

        gateway.remove(expectedAction.getId());  
        
        assertEquals(0, repository.count());
    }

    @Test
    @Transactional
    public void updateCategory() {
        final String expectedNewName = "Horror";
        final String expectedNewDescription = "The most watched category";

        final Category category = Category.newCategory(
            "Action Name",
            "Action Description",
            false
        );

        final var actualAction = repository.saveAndFlush(CategoryPersistence.from(category)); 

        assertThat(category.getIsActive()).isFalse();
        assertThat(category.getName()).isEqualTo("Action Name");
        assertThat(category.getDescription()).isEqualTo("Action Description");

        category.update(expectedNewName, expectedNewDescription, true);

        gateway.update(category);

        assertThat(category.getIsActive()).isTrue();
        assertThat(category.getName()).isEqualTo(expectedNewName);
        assertThat(category.getDescription()).isEqualTo(expectedNewDescription);

        final var persistedCategory = repository.findById(actualAction.getId()).get();

        assertThat(persistedCategory.getIsActive()).isTrue();
        assertThat(persistedCategory.getName()).isEqualTo(expectedNewName);
        assertThat(persistedCategory.getDescription()).isEqualTo(expectedNewDescription);
    }
}
