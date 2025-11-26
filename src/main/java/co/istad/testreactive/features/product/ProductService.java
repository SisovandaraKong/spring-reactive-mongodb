package co.istad.testreactive.features.product;

import co.istad.testreactive.features.product.dto.ProductRequest;
import co.istad.testreactive.features.product.dto.ProductResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Flux<ProductResponse> getAllProducts();
    Mono<ProductResponse> createProduct(ProductRequest productRequest);
    Flux<ProductResponse> streamProducts();
    Flux<ProductResponse> streamProductsRealtime();
}
