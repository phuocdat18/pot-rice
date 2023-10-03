package com.cg.cart;

import com.cg.cart.dto.CartResult;
import com.cg.cartDetail.dto.CartDetailUpdateParam;
import com.cg.model.Cart;
import com.cg.product.dto.ProductResult;
import com.cg.user.dto.UserResult;

import java.util.List;

    public interface ICartService {
        Cart findById(Long id);

        CartResult getById(Long id);

        List<CartResult> findAll();

        List<?> findAllByUserId(Long userId);

        Cart addToCart(CartDetailUpdateParam cartDetailUpdateParam, ProductResult productResult, UserResult userResult);

        List<?> findAllByUserIdAndCartId(Long userId, Long cartId);
    }
