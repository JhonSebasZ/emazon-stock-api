package com.emazon.emazon_stock_api.application.handler.category;

import com.emazon.emazon_stock_api.application.dto.category.CategoryRequest;
import com.emazon.emazon_stock_api.application.dto.category.CategoryResponse;
import com.emazon.emazon_stock_api.application.dto.pagination.PageResponse;

public interface ICategoryHandler {
    CategoryResponse saveCategory(CategoryRequest categoryRequest);
    PageResponse<CategoryResponse> listCategories(int page, int size, String sortDirection, String sortBy);
}
