package com.emazon.emazon_stock_api.domain.usecase;

import com.emazon.emazon_stock_api.domain.api.IBrandServicePort;
import com.emazon.emazon_stock_api.domain.models.Brand;
import com.emazon.emazon_stock_api.domain.exceptions.BrandExceptions;
import com.emazon.emazon_stock_api.domain.spi.IBrandPersistencePort;

public class BrandUseCase implements IBrandServicePort {

    private IBrandPersistencePort brandPersistencePort;
    private int MaxLengthName = 50;
    private int MaxLengthDescription = 120;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public boolean existsNameBrand(String name) {
        return brandPersistencePort.existsNameBrand(name);
    }

    @Override
    public void saveBrand(Brand brand) {
        if(existsNameBrand(brand.getName())){
            throw new BrandExceptions.ExistingBrandException("The existing brand");
        }
        validateCategory(brand.getName(), brand.getDescription());
    }

    public void validateCategory(String name, String description) {
        validateName(name);
        validateDescription(description);
    }

    public void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new BrandExceptions.InvalidBrandNameException("Brand name cannot be empty.");
        }
        if (name.length() > MaxLengthName) {
            throw new BrandExceptions.InvalidBrandNameException("The brand name cannot be more than 50 characters.");
        }
    }

    public void validateDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new BrandExceptions.InvalidBrandDescriptionException("Brand description cannot be empty.");
        }
        if (description.length() > MaxLengthDescription) {
            throw new BrandExceptions.InvalidBrandDescriptionException("The brand name cannot be more than 120 characters.");
        }
    }
}
