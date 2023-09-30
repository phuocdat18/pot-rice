package com.cg.cartDetail;


import com.cg.cart.CartMapper;
import com.cg.cart.dto.CartResult;
import com.cg.cartDetail.dto.CartDetailResult;
import com.cg.model.Cart;
<<<<<<< HEAD
import com.cg.model.Product;
=======
import com.cg.model.CartDetail;
>>>>>>> hoan-dev
import com.cg.cart.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
<<<<<<< HEAD
@Transactional
=======
>>>>>>> hoan-dev
@RequiredArgsConstructor
public class CartDetailServiceImpl implements ICartDetailService {
    private final CartMapper cartMapper;
    private final CartDetailRepository cartDetailRepository;
    private final CartRepository cartRepository;

<<<<<<< HEAD
//    @Override
//    public List<CartResult> findAll() {
//        List<Cart> entities = cartRepository.findAll();
//        return cartMapper.toDTOList(entities);
//    }
=======
    private final CartDetailRepository cartDetailRepository;
    private final CartRepository cartRepository;


    @Override
    public List<CartDetail> findAll() {
        return cartDetailRepository.findAll();
    }
>>>>>>> hoan-dev

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
    public List<CartDetailResult> findAllCartDetailDTO(Long id) {
        return cartDetailRepository.findAllCartDetailDTO(id);
    }

    @Override
    public List<CartDetail> findAllByCartId(Long cartId) {
        return cartDetailRepository.findCartDetailById(cartId);
    }


}