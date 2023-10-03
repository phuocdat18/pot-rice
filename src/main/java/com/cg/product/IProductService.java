package com.cg.product;

import com.cg.model.Product;
import com.cg.product.dto.ProductCreationParam;
import com.cg.product.dto.ProductFilter;
import com.cg.product.dto.ProductResult;
import com.cg.product.dto.ProductUpdateParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface IProductService {
    Page<ProductResult> findAllByFilter(ProductFilter filter, Pageable pageable);

    Product findById(Long id);

    ProductResult create(ProductCreationParam creationParam);

    ProductResult update(Long id, ProductUpdateParam updateParam);

    ProductResult getById(Long id);
}
