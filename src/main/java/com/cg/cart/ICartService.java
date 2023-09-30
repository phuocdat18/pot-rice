package com.cg.cart;

import com.cg.cart.dto.CartResult;
import com.cg.cart.dto.CartUpdateParam;
import com.cg.model.Cart;
import com.cg.model.Product;
import com.cg.model.User;

import java.util.List;

public interface ICartService {

    Cart addToCart(CartUpdateParam cartUpdateParam, Product product, User user);

    Cart findById(Long id);

    CartResult getById(Long id);

    List<CartResult> findAll();

    List<?> findAllByUserId(Long userId);
}
