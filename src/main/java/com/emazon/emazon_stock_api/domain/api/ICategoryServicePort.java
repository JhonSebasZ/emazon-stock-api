package com.emazon.emazon_stock_api.domain.api;

import com.emazon.emazon_stock_api.domain.models.Category;

public interface ICategoryServicePort {
    Category saveCategory(Category category);
}
