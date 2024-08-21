package com.emazon.emazon_stock_api.infrastructure.output.jpa.adapter;

import com.emazon.emazon_stock_api.domain.models.Category;
import com.emazon.emazon_stock_api.domain.spi.ICategoryPersistencePort;
import com.emazon.emazon_stock_api.infrastructure.output.jpa.entity.CategoryEntity;
import com.emazon.emazon_stock_api.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.emazon_stock_api.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.findByName(name).isPresent();
    }

    @Override
    public Category saveCategory(Category category) {
        CategoryEntity categoryEntity = categoryRepository.save(categoryEntityMapper.toCategoryEntity(category));
        return  categoryEntityMapper.toCategory(categoryEntity);
    }
}
