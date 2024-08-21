package com.emazon.emazon_stock_api.application.handler.category;

import com.emazon.emazon_stock_api.application.dto.category.CategoryRequest;
import com.emazon.emazon_stock_api.application.dto.category.CategoryResponse;

public interface ICategoryHandler {
    CategoryResponse saveCategory(CategoryRequest categoryRequest);
}
