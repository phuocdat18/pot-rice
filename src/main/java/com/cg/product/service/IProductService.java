package com.cg.product.service;

import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.product.mapper.ProductCreationParam;
import com.cg.product.mapper.ProductResult;
import com.cg.product.mapper.ProductUpdateParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IProductService  {

    ProductResult create(ProductCreationParam param, Category category);

    ProductResult update(Long id, ProductUpdateParam productUpdateParam, Category category);

    ProductResult getById(Long id);
    List<ProductResult> findAll();

    Product findById(Long id);

    Boolean existsProductById(Long id);

    Page<ProductResult> findAllProductDTOByKeyWordAndCategoryAndPrice (String search, List<Long> category, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);

    Page<ProductResult> findAllProductDTOByKeyWordAndCategory(String search, List<Long> category, Pageable pageable);

    Page<ProductResult> findAllProductDTOPage(Pageable pageable);

    Product save(Product product);

    void delete(Product product);

    void deleteById(Long id);
}
