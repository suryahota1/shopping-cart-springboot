package com.java_learning.shopping_cart.service.image;

import com.java_learning.shopping_cart.dto.ImageDto;
import com.java_learning.shopping_cart.exceptions.ImageNotFoundException;
import com.java_learning.shopping_cart.model.Image;
import com.java_learning.shopping_cart.model.Product;
import com.java_learning.shopping_cart.repository.ImageRepository;
import com.java_learning.shopping_cart.service.product.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService implements IImageService {

    private final ImageRepository imageRepository;
    private final ProductService productService;

    ImageService ( ImageRepository imageRepository, ProductService productService ) {
        this.imageRepository = imageRepository;
        this.productService = productService;
    }

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new ImageNotFoundException("Image not found"));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository :: delete, () -> {
            throw new ImageNotFoundException("Image not found");
        });
    }

    @Override
    public List<ImageDto> saveImages(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);
        List<ImageDto> imageDtos = new ArrayList<>();
        for ( MultipartFile file : files ) {
            try {
                Image image = new Image();

                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String downloadURL = "/api/v1/image/download/";

                Image savedImage = imageRepository.save(image);
                savedImage.setDownloadURL(downloadURL + savedImage.getId());
                savedImage = imageRepository.save(savedImage);

                ImageDto imageDto = new ImageDto();
                imageDto.setType(savedImage.getFileType());
                imageDto.setName(savedImage.getFileName());
                imageDto.setDownloadURL(savedImage.getDownloadURL());

                imageDtos.add(imageDto);
            } catch (RuntimeException | IOException | SQLException e ) {
                throw new RuntimeException(e);
            }
        }
        return imageDtos;
    }

    @Override
    public Image updateImage(MultipartFile file, Long imageId) {
        Image image = getImageById(imageId);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
            return  image;
        } catch ( Exception e ) {
            throw new RuntimeException(e);
        }
    }
}
