package org.example.springmvcproductcategory.service;

import org.example.springmvcproductcategory.dto.product.ProductRequest;
import org.example.springmvcproductcategory.dto.product.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProduct(String productName);
    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse findProductByID(int id );
    void deleteProduct(int productId);
    ProductResponse updateProduct(int id ,  ProductRequest productRequest);
}
