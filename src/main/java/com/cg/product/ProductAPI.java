package com.cg.product;

import com.cg.model.Category;
import com.cg.category.ICategoryService;
import com.cg.product.dto.ProductCreationParam;
import com.cg.product.dto.ProductResult;
import com.cg.product.dto.ProductUpdateParam;
import com.cg.product.service.IProductService;
import com.cg.utils.ValidateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/dashboard")
@RequiredArgsConstructor
public class ProductAPI {
    private final IProductService productService;
    private final ICategoryService categoryService;
    private final ValidateUtils validateUtils;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<?> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResult findProductById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping
    public ProductResult create(@RequestBody ProductCreationParam param, Category category) {
        return  productService.create(param, category);

    }

    @PatchMapping("/{id}")
    public ProductResult update(@PathVariable Long id, @RequestBody ProductUpdateParam param, Category category) {

        return productService.update(id, param, category);
    }

}
