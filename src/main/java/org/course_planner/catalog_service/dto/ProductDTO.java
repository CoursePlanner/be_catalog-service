package org.course_planner.catalog_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.course_planner.catalog_service.document.ProductDocument;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String productId;
    private String productName;
    private Long availableStockCount;
    private Double price;

    public ProductDTO(ProductDocument document) {
        this.productId = document.getProductId();
        this.productName = document.getProductName();
        this.availableStockCount = document.getProductQuantity();
        this.price = document.getProductPrice();
    }
}
