package com.cg.cart;

import com.cg.cart.cartDetail.CartItemRepository;
import com.cg.cart.cartDetail.dto.CartItemParam;
import com.cg.cart.dto.CartResult;
import com.cg.location.LocationMapper;
import com.cg.location.LocationRegionRepository;
import com.cg.location.dto.LocationRegionResult;
import com.cg.model.*;
import com.cg.order.IOrderItemService;
import com.cg.order.OrderMapper;
import com.cg.order.OrderRepository;
import com.cg.order.dto.OrderCreationParam;
import com.cg.product.IProductService;
import com.cg.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.rananu.shared.exceptions.NotFoundException;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService {
    private final IProductService productService;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final LocationRegionRepository locationRegionRepository;
    private final LocationMapper locationMapper;
    private final IOrderItemService orderItemService;

    @Override
    public List<CartResult> findAll() {
        List<Cart> entities = cartRepository.findAll();
        return cartMapper.toDTOList(entities);
    }

    @Override
    public List<LocationRegionResult> findAllLocation() {
        List<LocationRegion> entities = locationRegionRepository.findAll();
        return locationMapper.toDTOList(entities);
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
//    public void payment(Long cartId, CartItemParam cartItemParam, Long userId) {
//        Cart cart = findById(cartId);
//        List<CartItem> cartItems = cartItemRepository.findAllByCartId(cartId);
//
//        for (CartItem item : cartItems) {
//            Optional<Product> productOptional = Optional.ofNullable(productService.findById(item.getProductId()));
//            Product product = productOptional.orElseThrow(() -> new NotFoundException("Product not found"));
//
//            if (product.getQuantity() < item.getQuantity()) {
//                throw new NotFoundException("Số lượng sản phẩm " + item.getId() + " không đủ!");
//            }
//
//            Long quantityNew = product.getQuantity() - item.getQuantity();
//            product.setQuantity(quantityNew);
//            productRepository.save(product);
//        }
//
//        BigDecimal vat = cart.getTotalAmount().multiply(BigDecimal.valueOf(0.1));
//        BigDecimal totalBill = cart.getTotalAmount().add(vat).add(BigDecimal.valueOf(15000));
//
//        cartRepository.delete(cart);
//    }

    @Override
    @Transactional
    public void payment(Long cartId, CartItemParam cartItemParam, OrderCreationParam orderCreationParam, Long userId) {
        Cart cart = findById(cartId);
        List<CartItem> cartItems = cartItemRepository.findAllByCartId(cartId);

        for (CartItem item : cartItems) {
            Optional<Product> productOptional = Optional.ofNullable(productService.findById(item.getProductId()));
            Product product = productOptional.orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getQuantity() < item.getQuantity()) {
                throw new RuntimeException("Số lượng sản phẩm " + item.getId() + " không đủ!");
            }

            Long quantityNew = product.getQuantity() - item.getQuantity();
            product.setQuantity(quantityNew);
            productRepository.save(product);
        }

        BigDecimal totalAmount = BigDecimal.ZERO;
        for (CartItem item : cartItems) {
            BigDecimal amount = item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            totalAmount = totalAmount.add(amount);
        }

        BigDecimal vat = totalAmount.multiply(BigDecimal.valueOf(0.1));
        BigDecimal totalBill = totalAmount.add(vat).add(BigDecimal.valueOf(15000));

        Order order = orderRepository.save(new Order(totalBill, userId, orderCreationParam.getLocationRegion().toLocationRegion(null) , orderCreationParam.getStatus()));
        for (CartItem item : cartItems) {
            BigDecimal amount = item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            orderItemService.addOrderItem(new OrderItem(item.getProduct(), item.getTitle(), item.getUnit(), item.getPrice(), item.getQuantity(), amount, order), item);
            totalAmount = totalAmount.add(amount);
        }

//        Bill bill = billService.save(new Bill(totalBill, userOptional.get(), billReqDTO.getLocationRegionReqDTO().toLocationRegion(null) , billReqDTO.getStatus()));
//        for (CartDetail cartDetail : cartDetails) {
//            billDetailService.addBillDetail(new BillDetail(cartDetail.getProduct(), cartDetail.getTitle(), cartDetail.getUnit(), cartDetail.getPrice(), cartDetail.getQuantity(),cartDetail.getAmount(), bill), cartDetail);
//        }

        cartItemRepository.deleteAllByCartId(cartId);
    }

    @Override
    @Transactional
    public void addCartItem(Long cartId, CartItemParam param, Long userId) {
        Cart cart = findById(cartId);
        List<CartItem> cartItems = cartItemRepository.findAllByCartId(cartId);

        Long productId = param.getProductId();
        Long quantity = param.getQuantity();

        Product product = productService.findById(productId);

        boolean isProductExists = false;

        for (CartItem item : cartItems) {
            if (item.getProductId().equals(productId)) {
                Long existingQuantity = item.getQuantity();
                Long newQuantity = existingQuantity + quantity;
                item.setQuantity(newQuantity);
                isProductExists = true;
                break;
            }
        }

        if (!isProductExists) {
            CartItem cartItem = new CartItem();
            cartItem.setCartId(cartId)
                    .setCart(cart)
                    .setTitle(product.getTitle())
                    .setUnit(product.getUnit())
                    .setProduct(product)
                    .setProductId(productId)
                    .setQuantity(quantity)
                    .setPrice(product.getPrice());
            cartItemRepository.save(cartItem);
        }
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
