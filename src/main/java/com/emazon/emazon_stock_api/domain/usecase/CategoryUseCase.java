package com.emazon.emazon_stock_api.domain.usecase;

import com.emazon.emazon_stock_api.domain.api.ICategoryServicePort;
import com.emazon.emazon_stock_api.domain.exceptions.category.CategoryAlreadyExistsException;
import com.emazon.emazon_stock_api.domain.models.Category;
import com.emazon.emazon_stock_api.domain.exceptions.category.InvalidCategoryDescriptionException;
import com.emazon.emazon_stock_api.domain.exceptions.category.InvalidCategoryNameException;
import com.emazon.emazon_stock_api.domain.spi.ICategoryPersistencePort;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;
    private int MaxLengthName = 50;
    private int MaxLengthDescription = 90;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public Category saveCategory(Category category) {
        validateCategory(category.getName(), category.getDescription());
        return categoryPersistencePort.saveCategory(category);
    }

    public void validateCategory(String name, String description) {
        if(categoryPersistencePort.existsByName(name)){
            throw new CategoryAlreadyExistsException("Category '" + name + "' already exists");
        }
        validateName(name);
        validateDescription(description);
    }

    public void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new InvalidCategoryNameException("Category name cannot be empty.");
        }
        if (name.length() > MaxLengthName) {
            throw new InvalidCategoryNameException("Category name cannot be more than 50 characters.");
        }
    }

    public void validateDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new InvalidCategoryDescriptionException("Category description is required.");
        }
        if (description.length() > MaxLengthDescription) {
            throw new InvalidCategoryDescriptionException("Category description cannot be more than 90 characters.");
        }
    }
}
