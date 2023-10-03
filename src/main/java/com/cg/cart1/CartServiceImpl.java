package com.cg.cart1;

import com.cg.cart1.dto.CartResult;
import com.cg.cart1.dto.CartCreationParam;
import com.cg.cartItem.CartDetailRepository;
import com.cg.cartItem.dto.CartDetailUpdateParam;
import com.cg.exception.DataInputException;
import com.cg.model.*;

import com.cg.product.ProductMapper;
import com.cg.product.dto.ProductResult;
import com.cg.user.UserMapper;
import com.cg.user.dto.UserResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService {
    private final CartDetailRepository cartDetailRepository;
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final UserMapper userMapper;
    private final ProductMapper productMapper;

    @Override
    public List<CartResult> findAll() {
        List<Cart> entities = cartRepository.findAll();
        return cartMapper.toDTOList(entities);
    }

    @Override
    public Cart findById(Long id) {
        return cartRepository.findById(id).orElseThrow(() -> new DataInputException("id ko hop le"));
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

    @Transactional
    public CartResult order(Long userId, CartCreationParam creationParam) {
        Cart cart = cartMapper.toEntity(creationParam);
        cart = cartRepository.save(cart);
        return cartMapper.toDTO(cart);
    }

    @Override
    public Cart addToCart(CartDetailUpdateParam cartDetailUpdateParam, ProductResult product, UserResult user) {

        List<Cart> carts = cartRepository.findAllByUserId(user.getId());

        if (carts.isEmpty()) {
            Cart cartNew = new Cart();
            cartNew.setUser(userMapper.toEntity(user));
            cartNew.setTotalAmount(BigDecimal.ZERO);
            cartRepository.save(cartNew);

            BigDecimal price = product.getPrice();
            long quantity = cartDetailUpdateParam.getQuantity();
            BigDecimal amount = price.multiply(BigDecimal.valueOf(quantity));


            CartDetail cartDetail = new CartDetail();
            cartDetail.setCart(cartNew);
            cartDetail.setProduct(productMapper.toEntity(product));
            cartDetail.setTitle(product.getTitle());
            cartDetail.setPrice(price);
            cartDetail.setUnit(product.getUnit());
            cartDetail.setQuantity(quantity);
            cartDetail.setAmount(amount);
            cartDetailRepository.save(cartDetail);

            cartNew.setTotalAmount(amount);
            return cartRepository.save(cartNew);
        } else {
            if (cartDetailRepository.existsCartDetailByCart(carts.get(Math.toIntExact(user.getId())))) {
                CartDetail cartDetail = cartDetailRepository.findCartDetailsByProductAndCart(productMapper.toEntity(product), carts.get(Math.toIntExact(user.getId())));
                if (cartDetail != null) {
                    Cart cart = carts.get(Math.toIntExact(user.getId()));

                    BigDecimal price = product.getPrice();
                    long quantity = cartDetail.getQuantity() + cartDetailUpdateParam.getQuantity();
                    BigDecimal amount = price.multiply(BigDecimal.valueOf(quantity));

                    cartDetail.setQuantity(quantity);
                    cartDetail.setAmount(amount);

                    BigDecimal totalAmount = cart.getTotalAmount().add(amount);
                    cart.setTotalAmount(totalAmount);
                    return cartRepository.save(cart);
                } else {

                    BigDecimal price = product.getPrice();
                    long quantity = cartDetailUpdateParam.getQuantity();
                    BigDecimal amount = price.multiply(BigDecimal.valueOf(quantity));

                    Cart cart = carts.get(Math.toIntExact(user.getId()));
                    CartDetail cartDetailNew = new CartDetail();
                    cartDetailNew.setCart(cart);
                    cartDetailNew.setProduct(productMapper.toEntity(product));
                    cartDetailNew.setTitle(product.getTitle());
                    cartDetailNew.setPrice(product.getPrice());
                    cartDetailNew.setUnit(product.getUnit());
                    cartDetailNew.setQuantity(cartDetailUpdateParam.getQuantity());
                    cartDetailNew.setAmount(amount);
                    cartDetailRepository.save(cartDetailNew);

                    BigDecimal totalAmount = cart.getTotalAmount().add(amount);
                    cart.setTotalAmount(totalAmount);
                    return cartRepository.save(cart);
                }
            } else {

                BigDecimal price = product.getPrice();
                long quantity = cartDetailUpdateParam.getQuantity();
                BigDecimal amount = price.multiply(BigDecimal.valueOf(quantity));

                Cart cart = carts.get(Math.toIntExact(user.getId()));
                CartDetail cartDetailNew = new CartDetail();
                cartDetailNew.setCart(cart);
                cartDetailNew.setProduct(productMapper.toEntity(product));
                cartDetailNew.setTitle(product.getTitle());
                cartDetailNew.setPrice(product.getPrice());
                cartDetailNew.setUnit(product.getUnit());
                cartDetailNew.setQuantity(cartDetailUpdateParam.getQuantity());
                cartDetailNew.setAmount(amount);
                cartDetailRepository.save(cartDetailNew);

                BigDecimal totalAmount = cart.getTotalAmount().add(amount);
                cart.setTotalAmount(totalAmount);
                return cartRepository.save(cart);
            }
        }
    }

    @Override
    public List<?> findAllByUserIdAndCartId(Long userId, Long cartId) {
        return cartRepository.findAllByUserIdAndId(userId, cartId);
    }
}
