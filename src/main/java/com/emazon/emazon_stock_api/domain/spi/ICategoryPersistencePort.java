package com.emazon.emazon_stock_api.domain.spi;

import com.emazon.emazon_stock_api.domain.models.Category;

public interface ICategoryPersistencePort {
    boolean existsByName(String name);
    Category saveCategory(Category category);
}
