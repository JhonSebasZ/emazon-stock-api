package com.emazon.emazon_stock_api.infrastructure.category;

import com.emazon.emazon_stock_api.domain.models.Category;
import com.emazon.emazon_stock_api.domain.models.PaginatedResult;
import com.emazon.emazon_stock_api.infrastructure.exceptions.pagination.EmptyPageException;
import com.emazon.emazon_stock_api.infrastructure.exceptions.pagination.ExceedsNumberOfPageException;
import com.emazon.emazon_stock_api.infrastructure.output.jpa.adapter.CategoryJpaAdapter;
import com.emazon.emazon_stock_api.infrastructure.output.jpa.entity.CategoryEntity;
import com.emazon.emazon_stock_api.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.emazon_stock_api.infrastructure.output.jpa.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CategoryJpaAdapterTest {
    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private CategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    private CategoryJpaAdapter categoryJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExistsByName() {
        String name = "test";
        when(categoryRepository.findByName(name)).thenReturn(java.util.Optional.of(new CategoryEntity()));

        boolean exists = categoryJpaAdapter.existsByName(name);
        assertTrue(exists);
        verify(categoryRepository, times(1)).findByName(name);
    }

    @Test
    void testSaveCategory() {
        Category category = new Category(1L, "test", "test of handler");
        CategoryEntity categoryEntity = new CategoryEntity();
        when(categoryEntityMapper.toCategoryEntity(category)).thenReturn(categoryEntity);
        when(categoryRepository.save(categoryEntity)).thenReturn(categoryEntity);
        when(categoryEntityMapper.toCategory(categoryEntity)).thenReturn(category);

        Category savedCategory = categoryJpaAdapter.saveCategory(category);
        assertEquals(category, savedCategory);
        verify(categoryRepository, times(1)).save(categoryEntity);
    }

    @Test
    void testListCategories() {
        CategoryEntity categoryEntity = new CategoryEntity();
        List<CategoryEntity> categoryEntities = List.of(categoryEntity);
        Page<CategoryEntity> page = new PageImpl<>(categoryEntities, PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "name")), 1);
        PaginatedResult<Category> expectedResult = new PaginatedResult<>(List.of(new Category(1L, "test", "test of handler")), 0, 10, 1, 1, true);

        when(categoryRepository.findAll(any(Pageable.class))).thenReturn(page);
        when(categoryEntityMapper.toCategory(any(CategoryEntity.class))).thenReturn(new Category(1L, "test", "test of handler"));

        PaginatedResult<Category> result = categoryJpaAdapter.listCategories(0, 10, "asc", "name");

        assertThat(result.getContent()).usingRecursiveComparison().isEqualTo(expectedResult.getContent());

        verify(categoryRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void testValidateDataOfPaginationThrowsExceedsNumberOfPageException() {
        Page<CategoryEntity> page = new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 10), 0);

        assertThrows(ExceedsNumberOfPageException.class, () -> {
            categoryJpaAdapter.validateDataOfPagination(1, page);
        });
    }

    @Test
    void testValidateDataOfPaginationSuccess() {
        Page<CategoryEntity> page = new PageImpl<>(Collections.singletonList(new CategoryEntity()), PageRequest.of(0, 10), 10);

        categoryJpaAdapter.validateDataOfPagination(0, page);
    }
}
