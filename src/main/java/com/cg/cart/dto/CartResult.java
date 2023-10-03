package com.cg.cart.dto;

import com.cg.cartDetail.dto.CartDetailParam;
import com.cg.user.dto.UserResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class CartResult {
    private Long id;
    private UserResult userResult;
    private List<CartDetailParam> items;
}