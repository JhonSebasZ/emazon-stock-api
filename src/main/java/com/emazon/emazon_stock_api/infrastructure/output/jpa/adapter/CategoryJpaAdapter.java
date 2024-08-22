package com.emazon.emazon_stock_api.infrastructure.output.jpa.adapter;

import com.emazon.emazon_stock_api.domain.models.Category;
import com.emazon.emazon_stock_api.domain.models.PaginatedResult;
import com.emazon.emazon_stock_api.domain.spi.ICategoryPersistencePort;
import com.emazon.emazon_stock_api.infrastructure.exceptions.pagination.EmptyPageException;
import com.emazon.emazon_stock_api.infrastructure.exceptions.pagination.ExceedsNumberOfPageException;
import com.emazon.emazon_stock_api.infrastructure.output.jpa.entity.CategoryEntity;
import com.emazon.emazon_stock_api.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.emazon_stock_api.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public PaginatedResult<Category> listCategories(int page, int size, String sortDirection, String sortBy) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<CategoryEntity> categoryPage = categoryRepository.findAll(pageable);

        validateDataOfPagination(page, categoryPage);

        List<Category> categories = categoryPage.getContent()
                .stream()
                .map(categoryEntityMapper::toCategory)
                .collect(Collectors.toList());

        return new PaginatedResult<>(
                categories,
                categoryPage.getNumber(),
                categoryPage.getSize(),
                categoryPage.getTotalElements(),
                categoryPage.getTotalPages(),
                categoryPage.isLast()
        );
    }

    public void validateDataOfPagination(int page, Page<CategoryEntity> categoryPage){
        if (page >= categoryPage.getTotalPages()) {
            throw new ExceedsNumberOfPageException("Page number exceeds the total number of pages.");
        }

        if (categoryPage.getContent().isEmpty() && page > 0) {
            throw new EmptyPageException("Requested page does not contain any elements.");
        }
    }
}
