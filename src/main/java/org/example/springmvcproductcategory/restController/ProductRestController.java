package org.example.springmvcproductcategory.restController;

import lombok.RequiredArgsConstructor;
import org.example.springmvcproductcategory.dto.product.ProductRequest;
import org.example.springmvcproductcategory.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

/*
the RestController is the controller combine of controller and response body
it used for building RESTful web service
 */

/*

 */

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductService productService;

    // create a reusable method that for responding
    // we return as hashmap cus it structures as key pair value similar to json
    public HashMap<String, Object> response(Object object, String msg, int status) {
        HashMap<String, Object> response = new HashMap<>();
        // add the object, msg, status each response into the hashmap
        response.put("payload", object);
        response.put("message", msg);
        response.put("status", status);

        // we return the response that is the hashmap
        return response;
    }

    @GetMapping("/get-all")
    public HashMap<String, Object> getAllProduct(@RequestParam (defaultValue = "") String productName) {
        return response(
                productService.getAllProduct(productName),
                "Successful Retrieved Data",
                HttpStatus.OK.value());
    }

    @PostMapping("/new-product")
    public HashMap<String, Object> createNewProduct(@RequestBody ProductRequest request) {
        return response(
                productService.createProduct(request),
                "Created New Product Successfully",
                HttpStatus.OK.value()
        );
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HashMap<String, Object> findProductById(@PathVariable int id) {
        return response(
                productService.findProductByID(id),
                "Successfully Retrieved Record",
                HttpStatus.FOUND.value()
        );
    }

    @PatchMapping("/{id}")
    public HashMap<String, Object> updateProduct(@PathVariable int id, @RequestBody ProductRequest request) {
        return response(
                productService.updateProduct(id, request),
                "Update Product Successfully",
                HttpStatus.OK.value()
        );
    }

    @DeleteMapping("/{id}")
    public HashMap<String, Object> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return response(
                new ArrayList<>(),
                "Delete Successfully",
                HttpStatus.OK.value()
        );
    }

}
