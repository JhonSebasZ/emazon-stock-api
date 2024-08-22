package com.emazon.emazon_stock_api.infrastructure.output.jpa.mapper;

import com.emazon.emazon_stock_api.application.dto.category.CategoryRequest;
import com.emazon.emazon_stock_api.domain.models.Category;
import com.emazon.emazon_stock_api.infrastructure.output.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {

    CategoryEntity toCategoryEntity(Category category);
    Category toCategory(CategoryEntity categoryEntity);
}
