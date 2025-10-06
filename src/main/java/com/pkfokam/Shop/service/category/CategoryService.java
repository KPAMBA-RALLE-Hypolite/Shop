package com.pkfokam.Shop.service.category;

import com.pkfokam.Shop.exceptions.RessourcesNotFoundCategory;
import com.pkfokam.Shop.model.Category;
import com.pkfokam.Shop.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService{
    private final CategoryRepository categoryRepository;
    
    @Override
    public Category addCategory(String categoryName) {
        categoryRepository.findByName(categoryName).ifPresent(existingCategory -> {
            throw new RessourcesNotFoundCategory("Category with name '" + categoryName + "' already exists");
        });
        Category newCategory = new Category();
        newCategory.setName(categoryName);
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category updateCategory(String categoryName, Long categoryId) {
        return categoryRepository.findById(categoryId).map(oldcategory -> {;
            oldcategory.setName(categoryName);
            return categoryRepository.save(oldcategory);
        }).orElseThrow(()-> new RessourcesNotFoundCategory("Category not found"));
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.findById(categoryId).ifPresentOrElse(categoryRepository::delete,
        ()->{
            throw new RessourcesNotFoundCategory("Category not found");
        });

    }

    @Override
    public Category getCategoryById(Long id) throws Throwable {
        return categoryRepository.findById(id)
        .orElseThrow(()-> new RessourcesNotFoundCategory("Category not found"));
    }

    @Override
    public Category getCategoryByName(String name) throws Throwable {
        return categoryRepository.findByName(name)
                .orElseThrow(()-> new RessourcesNotFoundCategory("Category not found"));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
