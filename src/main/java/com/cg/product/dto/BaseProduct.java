package com.cg.product.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
public class BaseProduct {
    @NotNull(message = "{validate.product.title.notNull}")
    @Length(min = 5, max = 1000, message = "{validate.product.title.length}")
    private String title;

    @NotNull(message = "{validate.product.price.notNull}")
    private BigDecimal price;

    @NotNull(message = "{validate.product.quantity.notNull}")
    private Long quantity;

    @NotNull(message = "{validate.product.description.notNull}")
    private String description;

    @NotNull(message = "{validate.product.unit.notNull}")
    private String unit;

    @NotNull(message = "{validate.product.categoryId.notNull}")
    private Long categoryId;

    private MultipartFile avatar;
}
