package org.course_planner.catalog_service.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.course_planner.catalog_service.dto.ProductDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "product_catalog")
public class ProductDocument {
    @Id
    private String productId;
    @Indexed(unique = true)
    private String productName;
    private Double productPrice;
    private Long productQuantity;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Boolean isExpired = false;

    public ProductDocument(ProductDTO product, ProductDocument document) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.productQuantity = product.getAvailableStockCount();
        this.createdOn = document == null ? LocalDateTime.now() : document.getCreatedOn();
        this.updatedOn = LocalDateTime.now();
        this.productPrice = product.getPrice();
        this.isExpired = false;
    }
}
