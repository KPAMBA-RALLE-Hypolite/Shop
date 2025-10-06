package com.pkfokam.Shop.service.image;

import com.pkfokam.Shop.model.Image;
import org.springframework.web.multipart.MultipartFile;

public class ImageService implements IImageService{
    @Override
    public Image saveImage(MultipartFile file, long productId) {
        return null;
    }

    @Override
    public Image UpdateImage(MultipartFile file, long imageId) {
        return null;
    }

    @Override
    public Image getImageById(long id) {
        return null;
    }

    @Override
    public void deleteImage(long id) {

    }
}
