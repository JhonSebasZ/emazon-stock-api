package com.emazon.emazon_stock_api.application.handler;

import com.emazon.emazon_stock_api.application.dto.CategoryRequest;
import com.emazon.emazon_stock_api.application.dto.CategoryResponse;

import java.util.List;

public interface ICategoryHandler {
    void saveCategory(CategoryRequest categoryRequest);
}
