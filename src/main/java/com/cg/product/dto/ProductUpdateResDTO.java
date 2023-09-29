package com.cg.product.dto;



import com.cg.avatar.dto.ProductAvatarResDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ProductUpdateResDTO {

    private Long id;
    private String title;
    private String quantity;
    private BigDecimal price;
    private String description;
    private String unit;
    private String categoryTitle;
    private ProductAvatarResDTO avatar;

}
