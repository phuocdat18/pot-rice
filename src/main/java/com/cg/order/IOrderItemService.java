package com.cg.order;

import com.cg.model.OrderItem;
import com.cg.model.CartDetail;
import com.cg.order.dto.BillDetailResult;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IOrderItemService extends IGeneralService<OrderItem, Long> {

    OrderItem addBillDetail(OrderItem orderItem, CartDetail cartDetail);
    List<BillDetailResult> findAllBillDetailDTO(Long id);
    List<BillDetailResult> findBillDetailByBillId (Long id);

    List<BillDetailResult> findBillDetailByBillIdStatus(Long id);
}