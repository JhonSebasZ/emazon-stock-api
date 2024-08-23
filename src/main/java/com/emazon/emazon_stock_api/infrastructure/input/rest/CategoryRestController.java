package com.emazon.emazon_stock_api.infrastructure.input.rest;

import com.emazon.emazon_stock_api.application.dto.category.CategoryRequest;
import com.emazon.emazon_stock_api.application.dto.category.CategoryResponse;
import com.emazon.emazon_stock_api.application.dto.pagination.PageResponse;
import com.emazon.emazon_stock_api.application.handler.category.CategoryHandler;
import com.emazon.emazon_stock_api.application.mapper.category.ICategoryResponseMapper;
import com.emazon.emazon_stock_api.domain.usecase.CategoryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category/")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryHandler categoryHandler;
    private final ICategoryResponseMapper categoryResponseMapper;

    @Operation(
            summary = "List all categories",
            description = "Returns a paginated list of all categories with the option to sort by name in ascending or descending order."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of categories", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PageResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid pagination or sort parameters", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping()
    public ResponseEntity<PageResponse<CategoryResponse>> getAllCategory(
            @Parameter(
                    description = "Page number (0-based index)",
                    example = "0",
                    required = true
            )
            @RequestParam() int page,
            @Parameter(
                    description = "Number of categories per page",
                    example = "10",
                    required = true
            )
            @RequestParam() int size,
            @Parameter(
                    description = "Sort direction (asc for ascending, desc for descending)",
                    example = "asc",
                    required = true
            )
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        PageResponse<CategoryResponse> response = categoryHandler.listCategories(page, size, sortDirection, CategoryUseCase.ORDER_BY);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Add a new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created", content = @Content),
            @ApiResponse(responseCode = "409", description = "category already exists", content = @Content)
    })
    @PostMapping()
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryHandler.saveCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }
}
