package org.example.springmvcproductcategory.dto.product;

public record ProductRequest(
        String title,
        String description,
        String imageUrl,
        int categoryId,
        float price) {
}
