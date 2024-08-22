package com.emazon.emazon_stock_api.domain.usecase;

import com.emazon.emazon_stock_api.domain.api.ICategoryServicePort;
import com.emazon.emazon_stock_api.domain.exceptions.category.CategoryAlreadyExistsException;
import com.emazon.emazon_stock_api.domain.exceptions.category.CategoryNotFoundException;
import com.emazon.emazon_stock_api.domain.models.Category;
import com.emazon.emazon_stock_api.domain.exceptions.category.InvalidCategoryDescriptionException;
import com.emazon.emazon_stock_api.domain.exceptions.category.InvalidCategoryNameException;
import com.emazon.emazon_stock_api.domain.models.PaginatedResult;
import com.emazon.emazon_stock_api.domain.spi.ICategoryPersistencePort;

import java.util.List;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;
    private int maxLengthName = 50;
    private int maxLengthDescription = 90;
    public static final String ORDER_BY = "name";

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public Category saveCategory(Category category) {
        validateCategory(category.getName(), category.getDescription());
        return categoryPersistencePort.saveCategory(category);
    }

    @Override
    public PaginatedResult<Category> listCategories(int page, int size, String sortDirection, String sortBy) {
        PaginatedResult<Category> categories = categoryPersistencePort.listCategories(page, size, sortDirection, sortBy);
        if(categories.getContent().isEmpty()){
            throw new CategoryNotFoundException("There are no categories");
        }
        return categories;
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
        if (name.length() > maxLengthName) {
            throw new InvalidCategoryNameException("Category name cannot be more than 50 characters.");
        }
    }

    public void validateDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new InvalidCategoryDescriptionException("Category description is required.");
        }
        if (description.length() > maxLengthDescription) {
            throw new InvalidCategoryDescriptionException("Category description cannot be more than 90 characters.");
        }
    }
}
