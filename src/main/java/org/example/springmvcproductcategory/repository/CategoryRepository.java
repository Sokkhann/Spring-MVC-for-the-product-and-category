package org.example.springmvcproductcategory.repository;
import org.example.springmvcproductcategory.model.Category;
import org.example.springmvcproductcategory.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/*
for this package(repository) it serve with the data from outside database
we need to create this repo and as now we don't work with database
we create products as manual static category
when we use with real database we will retrieve real data from database
 */

/*
we add @Repository to indicate that this is the repository
it handles with the database access or other form of data storage
 */
@Repository
public class CategoryRepository {
    // here is the static categories
    // we create a category as list
    private final List<Category> categoryList = new ArrayList<>(){{
        add(Category.builder()
                .id(1)
                .title("Snack")
                .description("From Japan")
                .build());
        add(Category.builder()
                .id(2)
                .title("Drink")
                .description("From Korea")
                .build());
        add(Category.builder()
                .id(3)
                .title("Dessert")
                .description("For Hot season")
                .build());

    }};

    // method: get all categories
    public List<Category> getCategoryList() {
        // this is we just return the category list whenever this method is called
        return categoryList;
    }

    // method: add or create category
    public void addCategory(Category category) {
        // we just to add a new category into categoryList
        categoryList.add(category);
    }

    // method: update category(update the whole obj)
    public void updateCategory(Category category) {
        // we update by id we need to find id first
        // declare a variable to store the id that found
        int foundId = categoryList.indexOf(
                // in this we need to find the id of category by stream
                // we need to convert categoryList into stream cuz it has a lot of features
                categoryList.stream()
                        // this use for filter each product of categoryList if it matches with the provide id
                        .filter(pro -> pro.getId() == category.getId())
                        // return the category with provide id
                        .findFirst()
                        // otherwise return null(does not found that id)
                        .orElse(null)
                // the return will return to foundId variable to store
        );
        // this will update the category with provide id in the category list by new category
        // we in the param
        categoryList.set(foundId, category);
    }

    // method: delete category (delete by id)
    public void deleteCategory(int id) {
        // it is the same as update
        // we just filter for the category with the provide id
        int foundId = categoryList.indexOf(
                categoryList.stream()
                        .filter(pro -> pro.getId() == id)
                        .findFirst()
                        .orElse(null)
                // return the found id to store in foundId
        );

        // we will remove or delete the category with provide id here
        categoryList.remove(foundId);
    }
}
