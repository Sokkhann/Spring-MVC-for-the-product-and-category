package org.example.springmvcproductcategory.repository;

import lombok.*;
import org.example.springmvcproductcategory.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/*
for this package(repository) it serve with the data from outside database
we need to create this repo and as now we don't work with database
we create products as manual static product
when we use with real database we will retrieve real data from database
 */

/*
we add @Repository to indicate that this is the repository
it handles with the database access or other form of data storage
 */
@Repository
public class ProductRepository {

    // here is the static products
    // we create as products as list
    private final List<Product> productList = new ArrayList<>(){{
        add(Product.builder()
                .id(1)
                .title("Hanami")
                .description("Spicy Shrimp flavour")
                .price(0.5f)
                .imageUrl("hanami.jpg")
                .categoryId(1)
                .build());
        add(Product.builder()
                .id(2)
                .title("Lay's")
                .description("Yummy Taste")
                .categoryId(2)
                .price(0.2f)
                .imageUrl("lay's.jpg")
                .build());
        add(Product.builder()
                .id(3)
                .title("Oreo")
                .description("Blackpink edition")
                .categoryId(2)
                .price(0.2f)
                .imageUrl("oreo.jpg")
                .build());
    }};

    // method: get all products
    public List<Product> getProductList() {
        // this is we just return the product list whenever this method is called
        return productList;
    }

    // method: add or create product
    public void addProduct(Product product) {
        // we just to add a new product into productList
        productList.add(product);
    }

    // method: update product(update the whole obj)
    public void updateProduct(Product product) {
        // we update by id we need to find id first
        // declare a variable to store the id that found
        int foundId = productList.indexOf(
                // in this we need to find the id of product by stream
                // we need to convert productList into stream cuz it has a lot of features
                productList.stream()
                        // this use for filter each product of productList if it matches with the provide id
                        .filter(pro -> pro.getId() == product.getId())
                        // return the product with provide id
                        .findFirst()
                        // otherwise return null(does not found that id)
                        .orElse(null)
                        // the return will return to foundId variable to store
        );
        // this will update the product with provide id in the product list by new product
        // we in the param
        productList.set(foundId, product);
    }

    // method: delete product (delete by id)
    public void deleteProduct(int id) {
        // it is the same as update
        // we just filter for the product with the provide id
        int foundId = productList.indexOf(
                productList.stream()
                        .filter(pro -> pro.getId() == id)
                        .findFirst()
                        .orElse(null)
                        // return the found id to store in foundId
        );

        // we will remove or delete the product with provide id here
        productList.remove(foundId);
    }
}
