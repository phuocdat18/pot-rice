package com.cg.order;

import com.cg.order.dto.BillDetailResult;
import com.cg.model.OrderItem;
import com.cg.model.CartDetail;

import com.cg.cartDetail.CartDetailRepository;

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

    private final CartDetailRepository cartDetailRepository;



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
    public OrderItem addBillDetail(OrderItem orderItem, CartDetail cartDetail) {

        OrderItem orderItem1 = orderItemRepository.save(orderItem);
        cartDetailRepository.delete(cartDetail);
        return orderItem1;
    }

    @Override
    public List<BillDetailResult> findAllBillDetailDTO(Long id) {
        return orderItemRepository.findAllBillDetailDTO(id);
    }

    @Override
    public List<BillDetailResult> findBillDetailByBillId(Long id) {
        return orderItemRepository.findBillDetailByBillId(id);
    }
    @Override
    public List<BillDetailResult> findBillDetailByBillIdStatus(Long id) {
        return orderItemRepository.findBillDetailByBillIdStatus(id);
    }
}