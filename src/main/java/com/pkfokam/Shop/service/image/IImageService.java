package com.pkfokam.Shop.service.image;

import com.pkfokam.Shop.DTO.ImageDTO;
import com.pkfokam.Shop.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    List<ImageDTO> saveImages(List<MultipartFile> files, long productId) throws Throwable;

    Image updateImage(MultipartFile file, long imageId, String altText);

    Image getImageById(long id);
    void deleteImage(long id);

}
