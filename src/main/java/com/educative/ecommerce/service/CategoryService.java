package com.educative.ecommerce.service;

import com.educative.ecommerce.common.ApiResponse;
import com.educative.ecommerce.model.Category;
import com.educative.ecommerce.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    CategoryRepository categoryRepo;

    public ApiResponse createCategory(Category category) {
        try {
            if(category.getId() != null) {
                Optional<Category> tempCategoryOpt = categoryRepo.findById(category.getId());
                if (tempCategoryOpt.isPresent()) {
                    throw new DataIntegrityViolationException("The category id already exists");
                } else {
                    categoryRepo.save(category);
                }
            } else {
                categoryRepo.save(category);
            }
            return new ApiResponse(true, "success");
        } catch (DataIntegrityViolationException e) {
            logger.error("Error occurred in createCategory : {}", e.getMessage(), e);
        } catch (DataAccessException e) {
            logger.error("Error occurred in createCategory : {}", e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Error occurred in createCategory : {}", e.getMessage(), e);
        }
        return new ApiResponse(false, "failed");
    }

    public List<Category> getAllCategories() {
        List<Category> categories = null;
        try {
            categories = categoryRepo.findAll();
        } catch (EmptyResultDataAccessException e) {
            logger.warn("No categories found in this database");
            return Collections.emptyList();
        } catch (DataAccessException e) {
            logger.error("Error occurred in getAllCategories : {}", e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Error occurred in getAllCategories : {}", e.getMessage(), e);
        }
        return categories;
    }

    public Category updateCategoryDetails(Integer id, Category category) {
        Category tempCategory = categoryRepo.findById(id).get();
        tempCategory = tempCategory.builder()
                .categoryName(category.getCategoryName())
                .description(category.getDescription())
                .imageUrl(category.getImageUrl())
                .id(id)
                .build();
        categoryRepo.save(tempCategory);
        return tempCategory;
    }
}
