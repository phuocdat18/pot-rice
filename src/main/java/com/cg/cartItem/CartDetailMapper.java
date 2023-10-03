package com.cg.cartItem;

import com.cg.cartDetail.dto.CartDetailCreationParam;
import com.cg.cartDetail.dto.CartDetailResult;
import com.cg.model.CartDetail;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartDetailMapper {
    public CartDetail toEntity(CartDetailCreationParam param) {
        return new CartDetail()
                .setTitle(param.getTitle())
                .setUnit(param.getUnit())
                .setPrice(param.getPrice())
                .setQuantity(param.getQuantity())
                .setAmount(param.getAmount())
                ;
    }

    public CartDetailResult toDTO(CartDetail entity) {
        return new CartDetailResult()
                .setTitle(entity.getTitle())
                .setUnit(entity.getUnit())
                .setPrice(entity.getPrice())
                .setQuantity(entity.getQuantity())
                ;
    }

    public List<CartDetailResult> toDTOList(List<CartDetail> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
