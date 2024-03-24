package org.example.springmvcproductcategory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private String title;
    private String description;
    private String imageUrl;
    private int categoryId;
    private float price;
}
