package com.cg.cartDetail;

import com.cg.cartDetail.dto.CartDetailParam;
import com.cg.cartDetail.dto.CartDetailResult;
import com.cg.model.CartDetail;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartDetailMapper {
    public CartDetail toEntity(CartDetailParam param) {
        return new CartDetail()
                .setProductId(param.getProductId())
                .setQuantity(param.getQuantity())
                ;
    }

    public CartDetailResult toDTO(CartDetail entity) {
        return new CartDetailResult()
                .setTitle(entity.getTitle())
                .setPrice(entity.getPrice())
                .setQuantity(entity.getQuantity())
                ;
    }

    public List<CartDetailResult> toDTOList(List<CartDetail> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
