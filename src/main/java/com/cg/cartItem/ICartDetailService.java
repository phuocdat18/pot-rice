package com.cg.cartItem;

import com.cg.cartItem.dto.CartDetailResult;
import com.cg.model.CartDetail;

import java.util.List;
import java.util.Optional;

public interface ICartDetailService {
    List<CartDetail> findAll();

    Optional<CartDetail> findById(Long id);

    CartDetail create(CartDetail cartDetail);

    void delete(CartDetail cartDetail);

    void deleteById(Long id);

    List<CartDetailResult> findAllByCartId(Long cartId);
}