package com.cg.cart;

import com.cg.cart.dto.CartResult;
import com.cg.cart.dto.CartUpdateParam;
import com.cg.cartDetail.CartDetailRepository;
import com.cg.exception.DataInputException;
import com.cg.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService {
    private final CartDetailRepository cartDetailRepository;
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @Override
    public List<CartResult> findAll() {
        List<Cart> entities = cartRepository.findAll();
        return cartMapper.toDTOList(entities);
    }

    @Override
    public List<CartResult> findAllByUserId(Long userId) {
        List<Cart> entities = cartRepository.findAllByUserId(userId);
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
    public Cart addToCart(CartUpdateParam cartUpdateParam, Product product, User user) {

        List<Cart> carts = cartRepository.findAllByUserId(user.getId());

        if (carts.isEmpty()) {
            Cart cartNew = new Cart();
            cartNew.setUser(user);
            cartNew.setTotalAmount(BigDecimal.ZERO);
            cartRepository.save(cartNew);

            BigDecimal price = product.getPrice();
            long quantity = cartUpdateParam.getQuantity();
            BigDecimal amount = price.multiply(BigDecimal.valueOf(quantity));


            CartDetail cartDetail = new CartDetail();
            cartDetail.setCart(cartNew);
            cartDetail.setProduct(product);
            cartDetail.setTitle(product.getTitle());
            cartDetail.setPrice(product.getPrice());
            cartDetail.setUnit(product.getUnit());
            cartDetail.setQuantity(cartUpdateParam.getQuantity());
            cartDetail.setAmount(amount);
            cartDetailRepository.save(cartDetail);

            cartNew.setTotalAmount(amount);
            return cartRepository.save(cartNew);
        } else {
                if (cartDetailRepository.existsCartDetailByCart(carts.get())) {
                CartDetail cartDetail = cartDetailRepository.findCartDetailsByProductAndCart(product, carts.get());
                if (cartDetail != null) {
                    Cart cart = carts.get();

                    BigDecimal price = product.getPrice();
                    long quantity = cartDetail.getQuantity() + cartUpdateParam.getQuantity();
                    BigDecimal amount = price.multiply(BigDecimal.valueOf(quantity));


                    cartDetail.setQuantity(quantity);
                    cartDetail.setAmount(amount);

                    BigDecimal totalAmount = cart.getTotalAmount().add(amount);
                    cart.setTotalAmount(totalAmount);
                    return cartRepository.save(cart);
                } else {

                    BigDecimal price = product.getPrice();
                    long quantity = cartUpdateParam.getQuantity();
                    BigDecimal amount = price.multiply(BigDecimal.valueOf(quantity));

                    Cart cart = carts.get();
                    CartDetail cartDetailNew = new CartDetail();
                    cartDetailNew.setCart(cart);
                    cartDetailNew.setProduct(product);
                    cartDetailNew.setTitle(product.getTitle());
                    cartDetailNew.setPrice(product.getPrice());
                    cartDetailNew.setUnit(product.getUnit());
                    cartDetailNew.setQuantity(cartUpdateParam.getQuantity());
                    cartDetailNew.setAmount(amount);
                    cartDetailRepository.save(cartDetailNew);

                    BigDecimal totalAmount = cart.getTotalAmount().add(amount);
                    cart.setTotalAmount(totalAmount);
                    return cartRepository.save(cart);
                }
            } else {

                BigDecimal price = product.getPrice();
                long quantity = cartUpdateParam.getQuantity();
                BigDecimal amount = price.multiply(BigDecimal.valueOf(quantity));

                Cart cart = carts.get();
                CartDetail cartDetailNew = new CartDetail();
                cartDetailNew.setCart(cart);
                cartDetailNew.setProduct(product);
                cartDetailNew.setTitle(product.getTitle());
                cartDetailNew.setPrice(product.getPrice());
                cartDetailNew.setUnit(product.getUnit());
                cartDetailNew.setQuantity(cartUpdateParam.getQuantity());
                cartDetailNew.setAmount(amount);
                cartDetailRepository.save(cartDetailNew);

                BigDecimal totalAmount = cart.getTotalAmount().add(amount);
                cart.setTotalAmount(totalAmount);
                return cartRepository.save(cart);
            }
        }
    }
}
