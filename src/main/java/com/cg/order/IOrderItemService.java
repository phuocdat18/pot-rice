package com.cg.order;

import com.cg.model.OrderItem;
import com.cg.model.CartItem;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IOrderItemService extends IGeneralService<OrderItem, Long> {

    void addOrderItem(OrderItem orderItem, CartItem cartItem);

    List<OrderItem> findAllByOrderId(Long orderId);
}