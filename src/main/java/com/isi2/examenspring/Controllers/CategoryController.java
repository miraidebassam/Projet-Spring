package com.isi2.examenspring.Controllers;

import com.isi2.examenspring.Models.Category;
import com.isi2.examenspring.Models.Task;
import com.isi2.examenspring.Services.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // This means that this class is a Controller
@RequestMapping(path="/categorys") // This means URL's start with /demo (after Application path)
@Api(value = "Category Management", produces = "http")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @PostMapping("add")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @PutMapping("update/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        return categoryService.updateCategory(id, updatedCategory);
    }

    @DeleteMapping("delete/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

    @ApiOperation(value = "exemple", response = Category.class, code = 200)
    @PostMapping("addToCategory/{categoryId}/tasks")
    public ResponseEntity<String> addTaskToCategory(
            @PathVariable Long categoryId,
            @RequestBody Task task) {

        categoryService.addTaskToCategory(categoryId, task);
        return ResponseEntity.ok("Task added to category.");
    }
}
