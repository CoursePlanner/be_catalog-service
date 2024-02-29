package org.course_planner.catalog_service.dto;

import lombok.Getter;
import org.springframework.data.domain.Sort;

import java.util.List;

@Getter
public class SortableField {
    public static final List<String> SORTABLE_FIELDS = List.of("productPrice", "createdOn");
    public static final List<String> SORTABLE_DIRECTION = List.of(Sort.Direction.ASC.name(), Sort.Direction.DESC.name());
}
