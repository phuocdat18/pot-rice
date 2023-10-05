package com.cg.cart.cartDetail;

import com.cg.cart.cartDetail.dto.CartItemParam;
import com.cg.cart.cartDetail.dto.CartItemResult;
import com.cg.model.Cart;
import com.cg.model.CartItem;
import com.cg.model.Product;
import com.cg.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartDetailServiceImpl implements ICartDetailService {

    private final CartItemRepository cartItemRepository;
    private final IProductService productService;

    @Override
    public List<CartItemResult> findAll() {
        return null;// cartItemRepository.findAll();
    }

    @Override
    public CartItem findById(Long id) {
        return cartItemRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public CartItem getById(Long cartItemId) {
        return cartItemRepository.getById(cartItemId);
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

    @Override
    @Transactional
    public void changeQuantity(Long cartItemId, CartItemParam cartItemParam) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItemId);

        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            Long quantity = cartItemParam.getQuantity();

            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
        }
    }
}