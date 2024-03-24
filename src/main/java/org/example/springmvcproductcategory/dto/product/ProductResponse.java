package org.example.springmvcproductcategory.dto.product;

import lombok.Builder;

@Builder
public record ProductResponse(
        int id,
        String title,
        String description,
        String imageUrl,
        int categoryId,
        float price)
{
}
