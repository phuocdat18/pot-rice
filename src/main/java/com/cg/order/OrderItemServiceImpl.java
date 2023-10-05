package com.cg.order;

import com.cg.model.OrderItem;
import com.cg.model.CartItem;
import com.cg.cart.cartDetail.CartItemRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderItemServiceImpl implements IOrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    @Override
    public Optional<OrderItem> findById(Long id) {
        return orderItemRepository.findById(id);
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public void delete(OrderItem orderItem) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void addOrderItem(OrderItem orderItem, CartItem cartItem) {
        OrderItem orderItem1 = orderItemRepository.save(orderItem);
        cartItemRepository.delete(cartItem);
    }
}