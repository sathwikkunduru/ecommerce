package com.educative.ecommerce.controller;

import com.educative.ecommerce.common.ApiResponse;
import com.educative.ecommerce.model.Category;
import com.educative.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public Object createCategory(@RequestBody Category category) {
        ApiResponse response = categoryService.createCategory(category);
        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/list")
    public List<Category> listCategories() {
        return categoryService.getAllCategories();
    }
    @PostMapping("/update/{categoryId}")
    public Category updateCategory(@PathVariable("categoryId") Integer id, @RequestBody Category category) {
        return categoryService.updateCategoryDetails(id, category);
    }


}
/*
{
  "categoryName": "Mobiles",
  "description": "Used to call",
  "imageUrl": "https://t4.ftcdn.net/jpg/05/36/24/13/360_F_536241340_GsrsNhcWC0hyTVaJLilNafyDw6fl0cC8.jpg"
}
 */