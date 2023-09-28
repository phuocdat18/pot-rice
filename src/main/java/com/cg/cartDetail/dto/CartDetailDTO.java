package com.cg.cartDetail.dto;

<<<<<<< HEAD:src/main/java/com/cg/cartDetail/dto/CartDetailDTO.java
=======
import com.cg.model.Product;
import com.cg.product.dto.ProductDTO;
import lombok.AllArgsConstructor;
>>>>>>> hoan-dev:src/main/java/com/cg/dto/cart/CartDetailDTO.java
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
public class CartDetailDTO {
    private Long id;
    private String title;
    private String unit;
    private BigDecimal price;
    private Long quantity;
    private BigDecimal amount;

    public CartDetailDTO (Long id, String title, String unit, BigDecimal price, Long quantity, BigDecimal amount) {
        this.id = id;
        this.title = title;
        this.unit = unit;
        this.price = price;
        this.quantity = quantity;
        this.amount = amount;
    }
}
