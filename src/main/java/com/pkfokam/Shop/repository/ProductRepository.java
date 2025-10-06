package com.pkfokam.Shop.repository;

import com.pkfokam.Shop.model.Category;
import com.pkfokam.Shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Ici on navigue dans l’attribut "name" de Category
    List<Product> findByCategory_Name(String categoryName);

    List<Product> findByBrand(String brandName);

    List<Product> findByName(String name);

    List<Product> findByCategory_NameAndBrand(String categoryName, String brandName);

    List<Product> findByCategory_NameAndName(String categoryName, String name);

    List<Product> findByBrandAndName(String brandName, String name);

    long countByBrandAndName(String brandName, String name);

    List<Product> findByCategory_NameAndBrandAndName(String category, String brandName, String name);

}

