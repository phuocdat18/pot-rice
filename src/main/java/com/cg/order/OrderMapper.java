package com.cg.order;

import com.cg.model.Product;
import com.cg.order.dto.OrderCreationParam;
import com.cg.order.dto.OrderResult;
import com.cg.model.Order;
import com.cg.product.dto.BaseProduct;
import com.cg.product.dto.ProductResult;
import org.springframework.stereotype.Component;
import vn.rananu.shared.mappers.BaseMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper extends BaseMapper<OrderResult, Order, OrderCreationParam> {

}
