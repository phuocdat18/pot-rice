package com.cg.cart;

import com.cg.cart.dto.CartCreationParam;
import com.cg.cart.dto.CartResult;
import com.cg.model.Cart;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {
    public Cart toEntity(CartCreationParam creationParam) {
        return new Cart();
    }

    public CartResult toDTO(Cart entity) {
        return new CartResult();
    }

    public List<CartResult> toDTOList(List<Cart> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }
}