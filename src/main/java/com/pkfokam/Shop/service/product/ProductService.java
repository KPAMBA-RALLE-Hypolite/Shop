package com.pkfokam.Shop.service.product;

import com.pkfokam.Shop.exceptions.ProductNotFoundException;
import com.pkfokam.Shop.model.Category;
import com.pkfokam.Shop.model.Product;
import com.pkfokam.Shop.repository.ProductRepository;
import com.pkfokam.Shop.requests.AddNewProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;

    @Override
    public Product addProduct(AddNewProductRequest productRequest) {
        return null; // TODO: implémenter
    }

    private Product createProduct(AddNewProductRequest productRequest, Category category) {
        return new Product(
                productRequest.getName(),
                category,
                productRequest.getBrand(),
                productRequest.getDescription(),
                productRequest.getPrice()
        );
    }

    @Override
    public void updateProduct(Product product, Long productId) {
        // TODO: implémenter
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.findById(productId).ifPresentOrElse(
                productRepository::delete,
                () -> { throw new ProductNotFoundException("Product with id: " + productId + " not found"); }
        );
    }

    @Override
    public Product getProductById(Long id) throws Throwable {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + id + " not found"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory_Name(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brandName) {
        return productRepository.findByBrand(brandName);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brandName) {
        return productRepository.findByCategory_NameAndBrand(category, brandName);
    }

    @Override
    public List<Product> getProductsByCategoryAndName(String category, String name) {
        return productRepository.findByCategory_NameAndName(category, name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brandName, String name) {
        return productRepository.findByBrandAndName(brandName, name);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrandAndName(String category, String brandName, String name) {
        // tu peux chaîner les trois critères
        return productRepository.findByCategory_NameAndBrandAndName(category, brandName, name);
    }

    @Override
    public long countProductsByBrandAndName(String brandName, String name) {
        return productRepository.countByBrandAndName(brandName, name);
    }
}

