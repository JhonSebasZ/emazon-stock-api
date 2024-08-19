package com.emazon.emazon_stock_api.domain.api;

import com.emazon.emazon_stock_api.domain.entities.Category;

import java.util.List;

public interface ICategoryServicePort {
    boolean existsByName(String name);
    Category saveCategory(Category category);
}
