package com.emazon.emazon_stock_api.application.mapper.category;

import com.emazon.emazon_stock_api.application.dto.category.CategoryRequest;
import com.emazon.emazon_stock_api.domain.models.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoryRequestMapper {
    Category toCategory(CategoryRequest categoryRequest);
}
