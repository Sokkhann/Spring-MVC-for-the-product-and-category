package org.example.springmvcproductcategory.service;

import org.example.springmvcproductcategory.dto.category.CategoryRequest;
import org.example.springmvcproductcategory.dto.category.CategoryResponse;
import org.example.springmvcproductcategory.dto.product.ProductRequest;
import org.example.springmvcproductcategory.dto.product.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategories(String categoryName);
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    CategoryResponse findCategoryByID(int id );
    void deleteCategory(int categoryId);
    CategoryResponse updateCategory(int id ,  CategoryRequest categoryRequest);
}
