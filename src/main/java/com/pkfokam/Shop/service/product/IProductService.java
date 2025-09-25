package com.pkfokam.Shop.service.product;

import com.pkfokam.Shop.model.Category;
import com.pkfokam.Shop.model.Product;
import com.pkfokam.Shop.requests.AddNewProductRequest;

import java.util.List;

public interface IProductService {
    //ajout d'un produit
    Product addProduct(AddNewProductRequest productRequest);

    //mise a jour d'un produit
    void updateProduct(Product product, Long productId);
    
    //suppression d'un produit
    void deleteProduct(Long productId);
    
    //recherche d'un produit par son id
    Product getProductById(Long id) throws Throwable;
    List<Product>getAllProducts();
    List<Product>getProductsByCategory(String categoryName);
    List<Product>getProductsByBrand(String brandName);
    List<Product>getProductsByName(String name);
    List<Product>getProductsByCategoryAndBrand(String categoryName, String brandName);
    List<Product>getProductsByCategoryAndName(String categoryName, String name);
    List<Product>getProductsByBrandAndName(String brandName, String name);
    List<Product>getProductsByCategoryAndBrandAndName(String categoryName, String brandName, String name);
    long countProductsByBrandAndName(String brandName, String name);
}
