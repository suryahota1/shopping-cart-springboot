package com.java_learning.shopping_cart.service.image;

import com.java_learning.shopping_cart.dto.ImageDto;
import com.java_learning.shopping_cart.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> saveImages(List<MultipartFile> file, Long productId);
    Image updateImage(MultipartFile file, Long imageId);
}
