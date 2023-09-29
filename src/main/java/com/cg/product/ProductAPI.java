package com.cg.product;

import com.cg.model.Category;
<<<<<<< HEAD
import com.cg.category.ICategoryService;
import com.cg.product.mapper.ProductCreationParam;
import com.cg.product.mapper.ProductResult;
import com.cg.product.mapper.ProductUpdateParam;
import com.cg.product.service.IProductService;
import com.cg.utils.ValidateUtils;
import lombok.RequiredArgsConstructor;
=======
import com.cg.model.Product;
import com.cg.category.ICategoryService;
import com.cg.product.service.IProductService;
import com.cg.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
>>>>>>> 7c57b4faf17e5a2142b133e37376f6e77cb45cce
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
