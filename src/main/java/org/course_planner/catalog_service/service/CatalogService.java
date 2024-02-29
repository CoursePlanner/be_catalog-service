package org.course_planner.catalog_service.service;

import org.course_planner.catalog_service.dto.CatalogListRequest;
import org.course_planner.catalog_service.dto.CatalogListResponse;
import org.course_planner.catalog_service.dto.ProductDTO;
import reactor.core.publisher.Mono;

public interface CatalogService {
    Mono<ProductDTO> addNewProduct(ProductDTO request);

    Mono<CatalogListResponse> loadAllProducts(CatalogListRequest request);

    Mono<Void> updateProductQuantity(String productId, Long quanity);
}
