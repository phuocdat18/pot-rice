package com.cg.product.mapper;

import com.cg.model.Category;
import com.cg.model.ProductAvatar;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Setter
@Getter
@Accessors(chain = true)
public class ProductResult {

    private Long id;
    private String title;
    private BigDecimal price;
    private Long quantity;
    private String description;
    private String unit;
    private Category category;
    private ProductAvatar productAvatar;
}
