package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private List<Category> categories  = new ArrayList<>();
    private Long categoryId = 0L;

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(++categoryId);
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categories.stream()
                            .filter(c -> c.getCategoryId() ==  categoryId)
                             .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found!!!"));
        if(category != null) {
            categories.remove(category);
            return "CategoryId:  {%d} has been deleted".formatted(categoryId);
        }
        return null;
    }

    @Override
    public String updateCategory(Category category, Long categoryId) {
        Category existingCategory = categories.stream()
                .filter(c  ->  c.getCategoryId()  == categoryId)
                .findFirst().orElseThrow(() -> new ResponseStatusException( HttpStatus.NOT_FOUND,"Resource not found"));
        existingCategory.setCategoryName(category.getCategoryName());
        return "Resource has been updated";
    }
}
