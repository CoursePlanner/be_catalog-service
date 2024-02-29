package org.course_planner.catalog_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.course_planner.catalog_service.document.ProductDocument;
import org.course_planner.catalog_service.dto.CatalogListRequest;
import org.course_planner.catalog_service.dto.CatalogListResponse;
import org.course_planner.catalog_service.dto.CatalogPage;
import org.course_planner.catalog_service.dto.ProductDTO;
import org.course_planner.catalog_service.repository.CatalogRepository;
import org.course_planner.catalog_service.service.CatalogService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.LinkedList;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {
    private final CatalogRepository catalogRepository;

    @Override
    public Mono<ProductDTO> addNewProduct(ProductDTO request) {
        return catalogRepository.save(new ProductDocument(request, null))
                .map(ProductDTO::new);
    }

    @Override
    public Mono<CatalogListResponse> loadAllProducts(CatalogListRequest request) {
        CatalogListResponse response = new CatalogListResponse(new LinkedList<>(), null);
        PageRequest pageRequest = PageRequest.of(request.getPagination().getPage(),
                request.getPagination().getSize(),
                Sort.Direction.valueOf(request.getPagination().getSortDirection()),
                request.getPagination().getSortField());
        return catalogRepository.count().map(count -> {
                    CatalogPage page = new CatalogPage();
                    page.setPage(request.getPagination().getPage());
                    page.setSize(request.getPagination().getSize());
                    if (count > 0) {
                        page.setTotalPages(count / page.getSize());
                    } else {
                        page.setTotalPages(0L);
                    }
                    page.setTotalRecords(count);
                    page.setSortField(request.getPagination().getSortField());
                    page.setSortDirection(request.getPagination().getSortDirection());
                    response.setPagination(page);
                    return response;
                }).thenMany(catalogRepository.findAllWithPagination(pageRequest))
                .map(loadedProducts -> response.getProducts().add(new ProductDTO(loadedProducts)))
                .then(Mono.just(response));
    }

    @Override
    public Mono<Void> updateProductQuantity(String productId, Long quanity) {
        return null;
    }
}
