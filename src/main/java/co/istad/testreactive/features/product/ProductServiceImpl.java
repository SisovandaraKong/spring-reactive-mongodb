package co.istad.testreactive.features.product;

import co.istad.testreactive.domain.Product;
import co.istad.testreactive.features.product.dto.ProductRequest;
import co.istad.testreactive.features.product.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    @Override
    public Flux<ProductResponse> getAllProducts() {
        Flux<Product> productFlux = productRepository.findAll();
        return productFlux
                .filter(product -> !product.getIsDeleted())
                .map(product -> new ProductResponse(
                        product.getUuid(),
                        product.getName(),
                        product.getDescription()
                ));
    }

    @Override
    public Mono<ProductResponse> createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setUuid(UUID.randomUUID().toString());
        product.setName(productRequest.name());
        product.setDescription(productRequest.description());
        product.setIsDeleted(false);
        return productRepository.save(product)  // Returns Mono<Product>
                .map(savedProduct -> new ProductResponse(
                        savedProduct.getUuid(),
                        savedProduct.getName(),
                        savedProduct.getDescription()
                ));
    }
}
