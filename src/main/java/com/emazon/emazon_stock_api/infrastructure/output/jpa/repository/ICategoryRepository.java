package com.emazon.emazon_stock_api.infrastructure.output.jpa.repository;

import com.emazon.emazon_stock_api.infrastructure.output.jpa.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByName(String name);
}
