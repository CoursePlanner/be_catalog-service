package org.course_planner.catalog_service.controller;

import org.course_planner.catalog_service.dto.*;
import org.course_planner.catalog_service.service.CatalogService;
import org.course_planner.utils.exceptions.CatalogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CatalogController {
    @Autowired
    private CatalogService catalogService;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ProductDTO>> addNewProduct(@RequestBody ProductDTO request) {
        request.setProductId(null);
        if (request.getProductName() == null || request.getProductName().isBlank()) {
            throw new CatalogException("Product should have a name!", HttpStatus.BAD_REQUEST);
        }
        if (request.getPrice() == null || request.getPrice() < 1) {
            throw new CatalogException("Price should be greater than 0!", HttpStatus.BAD_REQUEST);
        }
        if (request.getAvailableStockCount() == null) {
            request.setAvailableStockCount(0L);
        }
        return catalogService.addNewProduct(request)
                .map(product -> new ResponseEntity<>(product, HttpStatus.CREATED));
    }

    @PostMapping(value = "/loadAll", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CatalogListResponse>> loadAllProducts(@RequestBody CatalogListRequest request) {
        if (request.getPagination() == null) {
            request.setPagination(new CatalogPage(0, 10));
        }
        if (request.getPagination().getSortField() == null || request.getPagination().getSortField().isBlank()) {
            request.getPagination().setSortField("price");
        }
        if (request.getPagination().getSortDirection() == null || request.getPagination().getSortDirection().isBlank()) {
            request.getPagination().setSortDirection("ASC");
        }
        if (!SortableField.SORTABLE_DIRECTION.contains(request.getPagination().getSortDirection())) {
            throw new CatalogException("Unsupported sort direction!", HttpStatus.BAD_REQUEST);
        }
        if (!SortableField.SORTABLE_FIELDS.contains(request.getPagination().getSortField())) {
            throw new CatalogException("Unsupported sort field!", HttpStatus.BAD_REQUEST);
        }
        return catalogService.loadAllProducts(request)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK));
    }
}
