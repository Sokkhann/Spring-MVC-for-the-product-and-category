package org.example.springmvcproductcategory.restController;
import lombok.RequiredArgsConstructor;
import org.example.springmvcproductcategory.dto.category.CategoryRequest;
import org.example.springmvcproductcategory.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

/*
the RestController is the controller combine of controller and response body
it used for building RESTful web service
 */

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryRestController {
    private final CategoryService categoryService;

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

    // we can get the category by this endpoint(get-all)
    @GetMapping("/get-all")
    public HashMap<String, Object> getAllCategories(@RequestParam(defaultValue = "") String categoryName) {
        return response(
                categoryService.getAllCategories(categoryName),
                "Successful Retrieved Data",
                HttpStatus.OK.value());
    }
    // this annotation use for post data to server
    @PostMapping("/create-new")
    public HashMap<String, Object> createNewCategory(@RequestBody CategoryRequest request) {
        return response(
                categoryService.createCategory(request),
                "Created New Category Successfully",
                HttpStatus.OK.value()
        );
    }

    // this annotation use for get the value (id)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HashMap<String, Object> findCategoryById(@PathVariable int id) {
        return response(
                categoryService.findCategoryByID(id),
                "Successfully Retrieved Record",
                HttpStatus.FOUND.value()
        );
    }

    // this annotation use for update the existing data
    @PatchMapping("/{id}")
    public HashMap<String, Object> updateCategory(@PathVariable int id, @RequestBody CategoryRequest request) {
        return response(
                categoryService.updateCategory(id, request),
                "Update Product Successfully",
                HttpStatus.OK.value()
        );
    }

    // this annotation use for delete the database on the @PathVariable(id)
    @DeleteMapping("/{id}")
    public HashMap<String, Object> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return response(
                new ArrayList<>(),
                "Delete Successfully",
                HttpStatus.OK.value()
        );
    }
}
