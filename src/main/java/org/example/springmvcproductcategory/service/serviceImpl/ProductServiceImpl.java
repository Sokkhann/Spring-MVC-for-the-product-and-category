package org.example.springmvcproductcategory.service.serviceImpl;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.example.springmvcproductcategory.dto.product.ProductRequest;
import org.example.springmvcproductcategory.dto.product.ProductResponse;
import org.example.springmvcproductcategory.model.Product;
import org.example.springmvcproductcategory.repository.ProductRepository;
import org.example.springmvcproductcategory.service.ProductService;
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
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private Product searchProductByID(int id){
        return  productRepository.getProductList()
                .stream().filter(p->p.getId()==id)
                .findFirst()
                .orElseThrow(()->new HttpClientErrorException(HttpStatus.NOT_FOUND,"Product doesn't exist!!"));
    }
    private ProductResponse mapProductToResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .imageUrl(product.getImageUrl())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .categoryId(product.getCategoryId())
                .build();
    }
    private Product mapRequestToProduct(ProductRequest request){
//        prevent null if the request fields are null
        return Product.builder()
                .title(request.title())
                .price(request.price())
                .imageUrl(request.imageUrl())
                .description(request.description())
                .categoryId(request.categoryId())
                .build();
    }

    @Override
    public List<ProductResponse> getAllProduct(String productName) {
        var product = productRepository.getProductList();
        if (!productName.isEmpty()){
            product = product.stream().filter(
                    pro-> pro.getTitle().toLowerCase().contains(productName.toLowerCase())
            ).toList();
        }
        return  product
                .stream()
                .map(this::mapProductToResponse).toList();
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Product newProduct = mapRequestToProduct(request);
        var maxID = productRepository.getProductList()
                .stream()
                .max(Comparator.comparingInt(Product::getId))
                .map(Product::getId);
        int newID=1;
        if(maxID.isPresent()) {
            newID = maxID.get() + 1;
        }
        newProduct.setId(newID);
        productRepository.addProduct(newProduct);

        return mapProductToResponse(newProduct);
    }

    @Override
    public ProductResponse findProductByID(int id) {
        return mapProductToResponse(searchProductByID(id));
    }

    @Override
    public void deleteProduct(int productId) {
        productRepository.deleteProduct(searchProductByID(productId).getId());
    }

    @Override
    public ProductResponse updateProduct(int id, ProductRequest productRequest) {
        var result = searchProductByID(id);
        result= mapRequestToProduct(productRequest);
        result.setId(id);
        productRepository.updateProduct(result);
        return mapProductToResponse(result);
    }
}
