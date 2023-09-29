package com.cg.api;
import com.cg.category.ICategoryService;
import com.cg.category.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/categories")
public class CategoryAPI {

    @Autowired
    private ICategoryService categoryService;


    @GetMapping
    public ResponseEntity<?> getAll() {
        List<CategoryDTO> categoryDTOList = categoryService.findAllCategoryDTO();

        return new ResponseEntity<>(categoryDTOList, HttpStatus.OK);
    }

}
