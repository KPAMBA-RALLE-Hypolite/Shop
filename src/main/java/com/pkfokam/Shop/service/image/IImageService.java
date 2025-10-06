package com.pkfokam.Shop.service.image;

import com.pkfokam.Shop.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image saveImage(MultipartFile file, long productId);
    Image UpdateImage(MultipartFile file, long imageId);
    Image getImageById(long id);
    void deleteImage(long id);

//
//    // New methods to get images by product attributes
//    List<Image> getImagesByProductId(long productId);
//    List<Image> getImagesByProductName(String productName);
//    List<Image> getImagesByProductBrand(String brand);

}
