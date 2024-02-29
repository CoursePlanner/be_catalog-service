package org.course_planner.catalog_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogListResponse {
    private List<ProductDTO> products;
    private CatalogPage pagination;
}
