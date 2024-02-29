package com.educative.ecommerce.service;

import com.educative.ecommerce.common.ApiResponse;
import com.educative.ecommerce.dto.ProductDto;
import com.educative.ecommerce.model.Category;
import com.educative.ecommerce.model.Product;
import com.educative.ecommerce.repository.CategoryRepository;
import com.educative.ecommerce.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    private Logger logger = LoggerFactory.getLogger(ProductService.class);

    public ApiResponse createProduct(ProductDto productDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ApiResponse(false, "category id does not exists");
        }
        if(productDto.getId() !=null) {
            Optional<Product> optionalProduct = productRepository.findById(productDto.getId());
            if (!optionalProduct.isPresent()) {
                return new ApiResponse(false, "product id does not exists");
            }
        }

        try {
            Product product = Product
                    .builder()
                    .id(productDto.getId())
                    .name(productDto.getName())
                    .category(optionalCategory.get())
                    .price(productDto.getPrice())
                    .description(productDto.getDescription())
                    .imageURL(productDto.getImageURL())
                    .build();
            productRepository.save(product);
            return new ApiResponse(true, "saved product details");
        } catch (DataIntegrityViolationException e) {
            logger.error("Error occurred in createProduct : {}", e.getMessage(), e);
        } catch (DataAccessException e) {
            logger.error("Error occurred in createProduct : {}", e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Error occurred in createProduct : {}", e.getMessage(), e);
        }

        return new ApiResponse(false, "failed to save product details");
    }
}
