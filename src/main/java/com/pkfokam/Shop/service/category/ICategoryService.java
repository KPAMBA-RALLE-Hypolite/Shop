package com.pkfokam.Shop.service.category;

import com.pkfokam.Shop.model.Category;

import java.util.List;

public interface ICategoryService {
    //ajout d'une categorie
    Category addCategory(String categoryName);
    //mise a jour d'une categorie
    Category updateCategory(String categoryName, Long categoryId);
    //suppression d'une categorie
    void deleteCategory(Long categoryId);
    //recherche d'une categorie 
    Category getCategoryById(Long id) throws Throwable;
    Category getCategoryByName(String name) throws Throwable;
    List<Category> getAllCategories();
    

}
