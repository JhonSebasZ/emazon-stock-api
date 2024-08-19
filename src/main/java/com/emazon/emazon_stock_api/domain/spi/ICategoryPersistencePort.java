package com.emazon.emazon_stock_api.domain.spi;

import com.emazon.emazon_stock_api.domain.entities.Category;

import java.util.List;

public interface ICategoryPersistencePort {
    boolean existsByName(String name);
    Category saveCategory(Category category);
}
