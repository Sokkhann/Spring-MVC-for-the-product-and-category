package org.example.springmvcproductcategory.service.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.example.springmvcproductcategory.dto.category.CategoryRequest;
import org.example.springmvcproductcategory.dto.category.CategoryResponse;
import org.example.springmvcproductcategory.model.Category;
import org.example.springmvcproductcategory.repository.CategoryRepository;
import org.example.springmvcproductcategory.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Comparator;
import java.util.List;

@Service
@RequestMapping
@RequiredArgsConstructor
@Builder
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private Category searchCategoryByID(int id){
        return  categoryRepository.getCategoryList()
                .stream().filter(p->p.getId()==id)
                .findFirst()
                .orElseThrow(()->new HttpClientErrorException(HttpStatus.NOT_FOUND,"Category doesn't exist!!"));
    }

    //this method use for convert simple category into category response
    private CategoryResponse mapCategoryToResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .description(category.getDescription())
                .build();
    }
    //this method use for convert category request into simple category
    private Category mapRequestToCategory(CategoryRequest request){
        return Category.builder()
                .title(request.title())
                .description(request.description())
                .build();
    }

    @Override
    public List<CategoryResponse> getAllCategories(String categoryName) {
        var product = categoryRepository.getCategoryList();
        if (!categoryName.isEmpty()){
            product = product.stream().filter(
                    pro-> pro.getTitle().toLowerCase().contains(categoryName.toLowerCase())
            ).toList();
        }
        return  product
                .stream()
                .map(this::mapCategoryToResponse).toList();
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category newCategory = mapRequestToCategory(categoryRequest);
        var maxID = categoryRepository.getCategoryList()
                .stream()
                .max(Comparator.comparingInt(Category::getId))
                .map(Category::getId);
        int newID=1;
        if(maxID.isPresent()) {
            newID = maxID.get() + 1;
        }
        newCategory.setId(newID);
        categoryRepository.addCategory(newCategory);

        return mapCategoryToResponse(newCategory);
    }

    @Override
    public CategoryResponse findCategoryByID(int id) {
        return mapCategoryToResponse(searchCategoryByID(id));
    }

    @Override
    public void deleteCategory(int categoryId) {
        categoryRepository.deleteCategory(searchCategoryByID(categoryId).getId());
    }

    @Override
    public CategoryResponse updateCategory(int id, CategoryRequest categoryRequest) {
        var result = searchCategoryByID(id);
        result= mapRequestToCategory(categoryRequest);
        result.setId(id);
        categoryRepository.updateCategory(result);
        return mapCategoryToResponse(result);
    }
}
