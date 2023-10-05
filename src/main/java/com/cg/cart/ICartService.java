package com.cg.cart;

import com.cg.cart.cartDetail.dto.CartItemParam;
import com.cg.cart.dto.CartResult;
import com.cg.location.dto.LocationRegionResult;
import com.cg.model.Cart;
import com.cg.order.dto.OrderCreationParam;

import javax.transaction.Transactional;
import java.util.List;

public interface ICartService {
    List<LocationRegionResult> findAllLocation();

    Cart findById(Long id);

    CartResult getById(Long id);

    List<CartResult> findAll();

    List<?> findAllByUserId(Long userId);

    void addCartItem(Long cartId, CartItemParam cartItemParam, Long userId);

    CartResult newCart(Long userId);

    void payment(Long cartId, CartItemParam cartItemParam,OrderCreationParam orderCreationParam, Long userId);
}
