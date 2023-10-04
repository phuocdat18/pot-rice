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

//    @Override
//    public void delete(CartItem cartItem) {
//        cartItemRepository.delete(cartItem);
//        List<CartItem> cartItems = cartItemRepository.findAll();
//        BigDecimal totalAmount = BigDecimal.ZERO;
//        for (CartItem cartItem1 : cartItems) {
//            totalAmount.add(cartItem1.getAmount());
//        }
//        Cart cart = cartItem.getCart();
//        cart.setTotalAmount(totalAmount);
//        cartRepository.save(cart);
//    }

    @Override
    public void deleteById(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public List<CartItem> findAllByCartId(Long cartId) {
        return cartItemRepository.findAllByCartId(cartId);
    }


}