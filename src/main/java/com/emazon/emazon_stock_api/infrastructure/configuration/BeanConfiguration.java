package com.emazon.emazon_stock_api.infrastructure.configuration;

import com.emazon.emazon_stock_api.domain.api.IBrandServicePort;
import com.emazon.emazon_stock_api.domain.api.ICategoryServicePort;
import com.emazon.emazon_stock_api.domain.spi.IBrandPersistencePort;
import com.emazon.emazon_stock_api.domain.spi.ICategoryPersistencePort;
import com.emazon.emazon_stock_api.domain.usecase.BrandUseCase;
import com.emazon.emazon_stock_api.domain.usecase.CategoryUseCase;
import com.emazon.emazon_stock_api.infrastructure.output.jpa.adapter.BrandJpaAdapter;
import com.emazon.emazon_stock_api.infrastructure.output.jpa.adapter.CategoryJpaAdapter;
import com.emazon.emazon_stock_api.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.emazon_stock_api.infrastructure.output.jpa.mapper.IBrandEntityMapper;
import com.emazon.emazon_stock_api.infrastructure.output.jpa.repository.IBrandRepository;
import com.emazon.emazon_stock_api.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public IBrandPersistencePort brandPersistencePort(){
        return  new BrandJpaAdapter(brandRepository, brandEntityMapper);
    }

    @Bean
    public IBrandServicePort brandServicePort(){
        return new BrandUseCase(brandPersistencePort());
    }
}
