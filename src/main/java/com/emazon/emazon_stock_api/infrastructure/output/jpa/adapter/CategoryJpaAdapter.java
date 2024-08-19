package com.emazon.emazon_stock_api.infrastructure.output.jpa.adapter;

import com.emazon.emazon_stock_api.domain.entities.Category;
import com.emazon.emazon_stock_api.domain.spi.ICategoryPersistencePort;
import com.emazon.emazon_stock_api.infrastructure.exception.CategoryAlreadyExistsException;
import com.emazon.emazon_stock_api.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.emazon_stock_api.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public boolean existsByName(String name) {
        return false;
    }

    @Override
    public Category saveCategory(Category category) {
        existCategory(category.getName());
        categoryRepository.save(categoryEntityMapper.toCategoryEntity(category));
        return  null;
    }

    public void existCategory(String name){
        if(categoryRepository.findByName(name).isPresent()){
            throw new CategoryAlreadyExistsException("Category exist");
        }
    }
}
