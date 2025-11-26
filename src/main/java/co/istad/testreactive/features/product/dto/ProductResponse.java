package co.istad.testreactive.features.product.dto;

public record ProductResponse(
        String uuid,
        String name,
        String description
) {
}
