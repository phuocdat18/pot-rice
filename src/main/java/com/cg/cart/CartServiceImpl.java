package com.cg.cart;

import com.cg.cart.cartDetail.CartItemRepository;
import com.cg.cart.cartDetail.dto.CartItemParam;
import com.cg.cart.cartDetail.dto.CartItemResult;
import com.cg.cart.dto.CartResult;
import com.cg.model.Cart;
import com.cg.model.CartItem;
import com.cg.model.Product;
import com.cg.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.rananu.shared.exceptions.NotFoundException;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService {
    private final IProductService productService;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @Override
    public List<CartResult> findAll() {
        List<Cart> entities = cartRepository.findAll();
        return cartMapper.toDTOList(entities);
    }

    @Override
    public Cart findById(Long id) {
        return cartRepository.findById(id).orElseThrow(() -> new NotFoundException("id ko hop le"));
    }

    @Override
    public CartResult getById(Long id) {
        Cart entity = findById(id);
        return cartMapper.toDTO(entity);
    }

    @Override
    public List<CartResult> findAllByUserId(Long userId) {
        List<Cart> entities = cartRepository.findAllByUserId(userId);
        return cartMapper.toDTOList(entities);
    }

//    @Override
//    @Transactional
//    public void addCartItem(Long cartId, CartItemParam param, Long userId) {
//        Cart cart = findById(cartId);
//        List<CartItem> cartItems = cartItemRepository.findAllByCartId(cartId);
//
//        Long productId = param.getProductId();
//        Long quantity = param.getQuantity();
//
//        Product product = productService.findById(productId);
//        if (cartItems.isEmpty()) {
//            CartItem cartItem = new CartItem();
//            cartItem.setCartId(cartId)
//                    .setCart(cart)
//                    .setTitle(product.getTitle())
//                    .setUnit(product.getUnit())
//                    .setProduct(product)
//                    .setProductId(productId)
//                    .setQuantity(quantity)
//                    .setPrice(product.getPrice())
//            ;
//            cartItemRepository.save(cartItem);
//        }
//        for (CartItem item : cartItems) {
//            if (item.getProductId().equals(productId)) {
//                item.setQuantity(quantity);
//            } else {
//                CartItem cartItem = new CartItem();
//                cartItem.setCartId(cartId)
//                        .setProduct(product)
//                        .setTitle(product.getTitle())
//                        .setUnit(product.getUnit())
//                        .setProductId(productId)
//                        .setQuantity(quantity)
//                        .setPrice(product.getPrice());
//            }
//            cartItemRepository.save(item);
//        }
//    }

    @Override
    @Transactional
    public void addCartItem(Long cartId, CartItemParam param, Long userId) {
        Cart cart = findById(cartId);
        List<CartItem> cartItems = cartItemRepository.findAllByCartId(cartId);

        Long productId = param.getProductId();
        Long quantity = param.getQuantity();
        Product product = productService.findById(productId);

        boolean found = false;

        for (CartItem item : cartItems) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity);
                found = true;
                break;
            }
        }

        if (!found) {
            CartItem cartItem = new CartItem();
            cartItem.setCartId(cartId)
                    .setCart(cart)
                    .setTitle(product.getTitle())
                    .setUnit(product.getUnit())
                    .setProduct(product)
                    .setProductId(productId)
                    .setQuantity(quantity)
                    .setPrice(product.getPrice());
            cartItems.add(cartItem);
        }
//        cart.setCartItems(cartItems);
        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public CartResult newCart(Long userId) {
        Cart entity = cartRepository.save(
                new Cart()
                        .setTotalAmount(BigDecimal.ZERO)
                        .setUserId(userId));
        return new CartResult()
                .setId(entity.getId());
    }
}
