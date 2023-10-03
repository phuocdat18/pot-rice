package com.cg.cartItem;

import com.cg.cart.CartMapper;
import com.cg.cart.CartRepository;
import com.cg.cartItem.dto.CartDetailResult;
import com.cg.model.Cart;
import com.cg.model.CartDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartDetailServiceImpl implements ICartDetailService {

    private final CartDetailRepository cartDetailRepository;
    private final CartRepository cartRepository;

    @Override
    public List<CartDetail> findAll() {
        return cartDetailRepository.findAll();
    }

    @Override
    public Optional<CartDetail> findById(Long id) {
        return cartDetailRepository.findById(id);
    }

    @Override
    public CartDetail create(CartDetail cartDetail) {
        return cartDetailRepository.save(cartDetail);
    }

    @Override
    public void delete(CartDetail cartDetail) {
        cartDetailRepository.delete(cartDetail);
        List<CartDetail> cartDetails = cartDetailRepository.findAll();
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (CartDetail cartDetail1 : cartDetails) {
            totalAmount.add(cartDetail1.getAmount());
        }
        Cart cart = cartDetail.getCart();
        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);
    }

    @Override
    public void deleteById(Long id) {
        cartDetailRepository.deleteById(id);
    }

    @Override
    public List<CartDetailResult> findAllByCartId(Long cartId) {
        return cartDetailRepository.findAllByCartId(cartId);
    }
}