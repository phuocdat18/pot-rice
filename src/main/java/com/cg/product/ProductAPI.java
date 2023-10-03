package com.cg.product;

import com.cg.product.dto.ProductCreationParam;
import com.cg.product.dto.ProductFilter;
import com.cg.product.dto.ProductResult;
import com.cg.product.dto.ProductUpdateParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductAPI {
    private final IProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<?> findAllByFilter(@RequestParam(name = "search", required = false) String search,
                           @RequestParam(name = "categoryIds", required = false) String categoryIdList,
                           @RequestParam(name = "minPrice", required = false) BigDecimal minPrice,
                           @RequestParam(name = "maxPrice", required = false) BigDecimal maxPrice,
                           @RequestParam("page") int page,
                           @RequestParam("pageSize") int pageSize) {
        List<Long> categoryIds = Arrays.stream(categoryIdList.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        ProductFilter filter = new ProductFilter().setKeyword(search)
                .setCategoryIds(categoryIds)
                .setMinPrice(minPrice)
                .setMaxPrice(maxPrice);
        return productService.findAllByFilter(filter, PageRequest.of(page - 1, pageSize));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResult findProductById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping
    public ProductResult create(@RequestBody ProductCreationParam creationParam) {
        return productService.create(creationParam);
    }

    @PatchMapping("/{id}")
    public ProductResult update(@PathVariable Long id, @RequestBody ProductUpdateParam param) {

        return productService.update(id, param);
    }

}
