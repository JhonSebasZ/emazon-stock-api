package com.emazon.emazon_stock_api.application.dto.brand;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandResponse {
    private long id;
    private String name;
    private String description;
}
