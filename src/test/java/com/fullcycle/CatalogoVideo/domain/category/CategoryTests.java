package com.fullcycle.CatalogoVideo.domain.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fullcycle.CatalogoVideo.domain.exception.DomainException;
import com.fullcycle.CatalogoVideo.runners.UnitTest;

import org.junit.jupiter.api.Test;

public class CategoryTests extends UnitTest {
    
    @Test
    public void throwDomainExceptionWhenNameIsNull() {
        assertThrows(DomainException.class, () -> Category.newCategory(null, "Description", true));
    }

    @Test
    public void throwDomainExceptionWhenNameIsBlank() {
        assertThrows(DomainException.class, () -> Category.newCategory("", "Description", true));
    }

    @Test
    public void createCategoryWithNameAndDescription() {
        final Category entity = Category.newCategory(
            "Name 1",
            "Description 2",
            true
        );

        assertNotNull(entity);
        assertEquals(entity.getName(), "Name 1");
        assertEquals(entity.getDescription(), "Description 2");
        assertTrue(entity.getIsActive());
        assertNotNull(entity.getCreatedAt());
        assertNotNull(entity.getUpdatedAt());
        assertNull(entity.getDeletedAt());
    }    

    @Test
    public void createCategoryWithNameAndDescriptionIsNull() {
        final Category entity = Category.newCategory(
            "Name 1",
            null,
            true
        );

        assertNotNull(entity);
        assertEquals(entity.getName(), "Name 1");
        assertNull(entity.getDescription());
        assertTrue(entity.getIsActive());
        assertNotNull(entity.getCreatedAt());
        assertNotNull(entity.getUpdatedAt());
        assertNull(entity.getDeletedAt());
    }

    @Test
    public void createCategoryAndIsDeactivate() {
        final Category entity = Category.newCategory(
            "Name 1",
            "Description 2",
            true
        );

        assertTrue(entity.getIsActive());
        assertNull(entity.getDeletedAt());

        entity.deactivate();

        assertFalse(entity.getIsActive());
        assertNotNull(entity.getDeletedAt()); 
        assertEquals(entity.getName(), "Name 1");
        assertEquals(entity.getDescription(), "Description 2");
        assertNotNull(entity.getCreatedAt());
        assertNotNull(entity.getUpdatedAt());
    }

    @Test
    public void createCategoryAndUpdate() {
        final Category entity = Category.newCategory(
            "Name 1",
            "Description 2",
            true
        );

        assertNotNull(entity);
        assertEquals(entity.getName(), "Name 1");
        assertEquals(entity.getDescription(), "Description 2");
        assertTrue(entity.getIsActive()); 
        assertNull(entity.getDeletedAt());        
        assertNotNull(entity.getCreatedAt());
        assertNotNull(entity.getUpdatedAt());

        entity.update("Name 2", "Desc", false);

        assertEquals(entity.getName(), "Name 2");
        assertEquals(entity.getDescription(), "Desc");        
        assertFalse(entity.getIsActive());     
        assertNotNull(entity.getDeletedAt()); 
        assertNotNull(entity.getCreatedAt());
        assertNotNull(entity.getUpdatedAt());
    }    
}
