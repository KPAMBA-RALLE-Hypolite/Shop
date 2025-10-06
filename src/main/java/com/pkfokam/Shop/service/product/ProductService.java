package com.pkfokam.Shop.service.product;

import com.pkfokam.Shop.exceptions.ProductNotFoundException;
import com.pkfokam.Shop.model.Category;
import com.pkfokam.Shop.model.Product;
import com.pkfokam.Shop.repository.CategoryRepository;
import com.pkfokam.Shop.repository.ProductRepository;
import com.pkfokam.Shop.requests.AddNewProductRequest;
import com.pkfokam.Shop.requests.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    /**
     * Ajouter un produit
     * - Si la catégorie existe déjà, on la réutilise
     * - Sinon, on la crée puis on l’associe au produit
     */
    @Override
    public Product addProduct(AddNewProductRequest productRequest) {
        // Vérifier si la catégorie existe
        Category category = categoryRepository.findByName(productRequest.getCategory().getName())
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(productRequest.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });

        // Créer le produit
        Product product = new Product(
                productRequest.getName(),
                category,
                productRequest.getBrand(),
                productRequest.getDescription(),
                productRequest.getPrice()
        );

        return productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product, Long productId) {

    }

    /**
     * Mettre à jour un produit existant
     * - On vérifie que le produit existe
     * - On met à jour ses champs
     * - On sauvegarde
     */
    @Override
    public Product updateProduct(UpdateProductRequest request, Long productId) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + productId + " not found"));

        // Mettre à jour les champs
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setPrice(request.getPrice());

        // Vérifier si la catégorie existe
        Category category = categoryRepository.findByName(request.getCategory().getName())
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });

        existingProduct.setCategory(category);

        return productRepository.save(existingProduct);
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
        return productRepository.findByCategory_NameAndBrandAndName(category, brandName, name);
    }

    @Override
    public long countProductsByBrandAndName(String brandName, String name) {
        return productRepository.countByBrandAndName(brandName, name);
    }
}
