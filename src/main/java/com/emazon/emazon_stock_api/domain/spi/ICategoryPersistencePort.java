package com.emazon.emazon_stock_api.domain.spi;

import com.emazon.emazon_stock_api.domain.models.Category;
import com.emazon.emazon_stock_api.domain.models.PaginatedResult;

import java.util.List;

public interface ICategoryPersistencePort {
    boolean existsByName(String name);
    Category saveCategory(Category category);
    PaginatedResult<Category> listCategories(int page, int size, String sortDirection, String sortBy);
}
