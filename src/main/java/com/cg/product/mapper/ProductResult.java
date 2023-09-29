package com.cg.product.mapper;

<<<<<<< HEAD:src/main/java/com/cg/product/mapper/ProductResult.java
import com.cg.model.Category;
import com.cg.model.ProductAvatar;
=======

import com.cg.avatar.dto.ProductAvatarResDTO;
import lombok.AllArgsConstructor;
>>>>>>> 7c57b4faf17e5a2142b133e37376f6e77cb45cce:src/main/java/com/cg/product/dto/ProductCreateResDTO.java
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
