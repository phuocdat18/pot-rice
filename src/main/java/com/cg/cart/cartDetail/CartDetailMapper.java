package com.cg.cart.cartDetail;

import com.cg.cart.cartDetail.dto.CartItemParam;
import com.cg.cart.cartDetail.dto.CartItemResult;
import com.cg.model.CartItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartDetailMapper {
    public CartItem toEntity(CartItemParam param) {
        return new CartItem()
                .setProductId(param.getProductId())
                .setQuantity(param.getQuantity())
                ;
    }

    public CartItemResult toDTO(CartItem entity) {
        return new CartItemResult()
                .setTitle(entity.getTitle())
                .setPrice(entity.getPrice())
                .setQuantity(entity.getQuantity())
                ;
    }

    public List<CartItemResult> toDTOList(List<CartItem> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
