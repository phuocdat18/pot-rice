package com.cg.order;

import com.cg.model.OrderItem;
import com.cg.model.CartDetail;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IOrderItemService extends IGeneralService<OrderItem, Long> {

    OrderItem addBillDetail(OrderItem orderItem, CartDetail cartDetail);
}