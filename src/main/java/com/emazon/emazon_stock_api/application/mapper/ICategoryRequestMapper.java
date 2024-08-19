package com.emazon.emazon_stock_api.application.mapper;

import com.emazon.emazon_stock_api.application.dto.CategoryRequest;
import com.emazon.emazon_stock_api.domain.entities.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoryRequestMapper {
    Category toCategory(CategoryRequest categoryRequest);
}
