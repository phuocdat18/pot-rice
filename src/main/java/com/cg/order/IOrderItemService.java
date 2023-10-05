package com.cg.order;

import com.cg.model.OrderItem;
import com.cg.model.CartItem;
import com.cg.service.IGeneralService;

public interface IOrderItemService extends IGeneralService<OrderItem, Long> {

    void addOrderItem(OrderItem orderItem, CartItem cartItem);
}