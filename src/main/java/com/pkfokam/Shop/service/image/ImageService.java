package com.pkfokam.Shop.service.image;

import com.pkfokam.Shop.DTO.ImageDTO;
import com.pkfokam.Shop.exceptions.ImageNotFoundException;
import com.pkfokam.Shop.model.Image;
import com.pkfokam.Shop.model.Product;
import com.pkfokam.Shop.repository.ImageRepository;
import com.pkfokam.Shop.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

    private final ImageRepository imageRepository;
    private final IProductService productService;

    @Override
    public List<ImageDTO> saveImages(List<MultipartFile> files, long productId) throws Throwable {
        Product product = productService.getProductById(productId);

        List<ImageDTO> imageDTOs = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;

            Image image = new Image();
            image.setName(file.getOriginalFilename());
            image.setAltText("Image du produit: " + product.getName());
            image.setProduct(product);

            try {
                image.setData(file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Erreur lors de la lecture du fichier : " + file.getOriginalFilename(), e);
            }

            // Sauvegarde initiale
            Image savedImage = imageRepository.save(image);

            // Génération de l'URL
            savedImage.setUrl("/images/" + savedImage.getId());
            savedImage = imageRepository.save(savedImage);

            imageDTOs.add(new ImageDTO(savedImage.getId(), savedImage.getName(), savedImage.getUrl()));
        }

        return imageDTOs;
    }

    @Override
    public Image updateImage(MultipartFile file, long imageId, String altText) {
        Image existingImage = getImageById(imageId);

        existingImage.setName(file.getOriginalFilename());
        existingImage.setAltText(altText);

        try {
            if (file.getBytes() != null && file.getBytes().length > 0) {
                existingImage.setData(file.getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la lecture du fichier", e);
        }

        existingImage.setUrl("/images/" + existingImage.getId());

        return imageRepository.save(existingImage);
    }

    @Override
    public Image getImageById(long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ImageNotFoundException("Image with Id :" + id + " not found"));
    }

    @Override
    public void deleteImage(long id) {
        imageRepository.findById(id)
                .ifPresentOrElse(imageRepository::delete,
                        () -> {
                            throw new ImageNotFoundException("Image with Id :" + id + " not found");
                        });}
    
}