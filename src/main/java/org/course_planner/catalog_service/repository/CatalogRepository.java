package org.course_planner.catalog_service.repository;

import org.course_planner.catalog_service.document.ProductDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CatalogRepository extends ReactiveMongoRepository<ProductDocument, String> {
    @Query("{}")
    Flux<ProductDocument> findAllWithPagination(Pageable pageable);
}
