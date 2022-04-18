package com.fullcycle.CatalogoVideo.domain.category;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fullcycle.CatalogoVideo.domain.exception.NotBlankException;
import com.fullcycle.CatalogoVideo.domain.exception.NotNullException;

public class Category {

    private UUID id;
    private String name;
    private String description;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public Category(
        final UUID id,
        final String name, 
        final String description, 
        final Boolean isActive, 
        final LocalDateTime createdAt, 
        final LocalDateTime updatedAt, 
        final LocalDateTime deletedAt
    ) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
        this.setIsActive(isActive);
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);
        this.setDeletedAt(deletedAt);
    }

    public static Category newCategory(final String name, final String description, final Boolean isActive) {
        final LocalDateTime now = LocalDateTime.now();
        return new Category(UUID.randomUUID(), name, description, isActive, now, now, null);
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }
    
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }
    
    public LocalDateTime getDeletedAt() {
        return this.deletedAt;
    }

    public Boolean active() {
        this.setDeletedAt(null);
        return this.isActive = true;
    }

    public Boolean deactivate() {
        if (getDeletedAt() == null) {
            this.setDeletedAt(LocalDateTime.now());
        }
        return this.isActive = false;
    }

    public void update(final String name, final String description, final Boolean isActive) {
        this.setName(name);
        this.setDescription(description);
        this.setUpdatedAt(LocalDateTime.now());
        if (isActive != null && isActive) {
            this.active();
        } else {
            this.deactivate();
        }
    }

    private void setId(final UUID id) {
        this.id = id;
    }

    private void setName(final String name) {
        if (name == null) {
            throw new NotNullException("'name'");
        }
        if (name.isEmpty()) {
            throw new NotBlankException("'name'");
        }

        this.name = name;
    }

    private void setDescription(final String description) {
        this.description = description;
    }

    private void setCreatedAt(final LocalDateTime date) {
        this.createdAt = date != null ? date : LocalDateTime.now();
    }

    private void setUpdatedAt(final LocalDateTime date) {
        this.updatedAt = date != null ? date : LocalDateTime.now();
    }

    private void setDeletedAt(final LocalDateTime date) {
        this.deletedAt = date;
    }
    
    private void setIsActive(final Boolean active) {
        this.isActive = active;
    }
}
