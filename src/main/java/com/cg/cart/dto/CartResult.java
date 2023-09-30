package com.cg.cart.dto;

import com.cg.cartDetail.dto.CartDetailCreationParam;
import com.cg.location.dto.LocationRegionParam;
import com.cg.model.CartDetail;
import com.cg.model.OrderStatus;
import com.cg.order.dto.OrderItemParam;
import com.cg.user.dto.UserResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CartResult {
    private Long id;
    private List<CartDetailCreationParam> items;
    private UserResult userResult;
}