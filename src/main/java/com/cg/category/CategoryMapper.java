package com.cg.category;

import com.cg.model.Category;
import com.cg.model.dto.category.CategoryDTO;

public class CategoryMapper {

    private static CategoryMapper INSTANCE;

    public static CategoryMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CategoryMapper();
        }

        return INSTANCE;
    }

    public Category toEntity(CategoryDTO roleDTO) {
        Category category = new Category();
        category.setTitle(roleDTO.getTitle());
        return category;
    }

    public CategoryDTO toDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setTitle(category.getTitle());
        categoryDTO.setId(category.getId());
        return categoryDTO;
    }
}
