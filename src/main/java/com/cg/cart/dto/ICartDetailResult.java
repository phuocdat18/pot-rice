<<<<<<< HEAD:src/main/java/com/cg/cart/dto/ICartDetailResult.java
package com.cg.cart.dto;
=======
package com.cg.dto.cart;
>>>>>>> hoan-dev:src/main/java/com/cg/dto/cart/ICartDetailResult.java

import java.math.BigDecimal;

public interface ICartDetailResult {
    Long getId();

    String getTitle();

    String getUnit();

    BigDecimal getPrice();

    Long getQuantity();

    BigDecimal getAmount();
}
