package com.cg.cart.cartDetail;

import com.cg.cart.cartDetail.dto.CartItemResult;
import com.cg.model.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartDetailServiceImpl implements ICartDetailService {

    private final CartItemRepository cartItemRepository;

    @Override
    public List<CartItemResult> findAll() {
        return null;// cartItemRepository.findAll();
    }

    @Override
    public CartItem findById(Long id) {
        return cartItemRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public CartItem getById(Long id) {
        return null;
    }

    public CartItem create(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }


    @Override
    public void deleteById(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public List<CartItem> findAllByCartId(Long cartId) {
        return cartItemRepository.findAllByCartId(cartId);
    }


}