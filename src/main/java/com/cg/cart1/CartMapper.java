package com.cg.cart1;

import com.cg.cart1.dto.CartCreationParam;
import com.cg.cart1.dto.CartResult;
import com.cg.cart1.dto.CartUpdateParam;
import com.cg.model.Cart;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {
    public Cart toEntity(CartCreationParam creationParam) {
        return new Cart();
    }

    public void transferFields(Cart entity, CartUpdateParam cartUpdateParam){
    }

    public CartResult toDTO(Cart entity) {
        return new CartResult();
    }

    public List<CartResult> toDTOList(List<Cart> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }
}