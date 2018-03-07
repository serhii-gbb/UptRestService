package com.tech.up.controller;

import com.tech.up.entity.Category;
import com.tech.up.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> getCategory(@PathVariable("id") int id) {
        Category byId = categoryService.getById(id);

        return new ResponseEntity<>(byId, HttpStatus.OK);
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
    public String saveCategory(@RequestBody Category category) {
        category.setId(ProductController.DEFAULT_DB_ID);
        categoryService.save(category);

        return "Category saved successfully!";
    }


    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = "application/json")
    public String updateCategory(@RequestBody Category category) {
        categoryService.update(category);

        return "Category updated successfully";
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteCategory(@PathVariable("id") int id) {
        categoryService.delete(id);

        return "Category deleted successfully!";
    }


    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getByName(@RequestParam("keyword") String keyword) {
        List<Category> categoriesByName = categoryService.getByKeyword(keyword);

        return new ResponseEntity<>(categoriesByName, HttpStatus.OK);
    }

}
