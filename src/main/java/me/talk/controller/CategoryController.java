package me.talk.controller;

import lombok.RequiredArgsConstructor;
import me.talk.model.Category;
import me.talk.service.CategoryService;
import me.talk.service.ValidationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final ValidationService validationService;

    @GetMapping("/all")
    public List<Category> getAllCategory(@RequestParam String name,
                                         @RequestParam String password) {
        validationService.validateUser(name, password);
        return categoryService.getAllCategory();
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category,
                                   @RequestParam String name,
                                   @RequestParam String password) {
        validationService.validateUser(name, password);
        return categoryService.createCategory(category);
    }

    @PutMapping
    public Category updateCategory(@RequestBody Category category,
                                   @RequestParam String name,
                                   @RequestParam String password) {
        validationService.validateUser(name, password);
        return categoryService.updateCategory(category);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable int id,
                                    @RequestParam String name,
                                    @RequestParam String password) {
        validationService.validateUser(name, password);
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id,
                               @RequestParam String name,
                               @RequestParam String password) {
        validationService.validateUser(name, password);
        categoryService.deleteCategory(id);
    }
}
