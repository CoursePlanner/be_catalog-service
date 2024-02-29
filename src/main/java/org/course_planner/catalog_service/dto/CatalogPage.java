package org.course_planner.catalog_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogPage {
    private Integer page;
    private Integer size;
    private Long totalRecords;
    private Long totalPages;
    private String sortDirection;
    private String sortField;

    public CatalogPage(int page, int size) {
        this.page = page;
        this.size = size;
    }
}
