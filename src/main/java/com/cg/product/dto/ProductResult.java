package com.cg.product.dto;

import com.cg.model.Category;
import com.cg.model.ProductAvatar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductResult {

    private Long id;
    private String title;
    private BigDecimal price;
    private Long quantity;
    private String description;
    private String unit;
    private Category category;
    private ProductAvatar avatar;
}
