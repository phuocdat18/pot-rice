package com.cg.order.dto;

import com.cg.location.dto.LocationRegionParam;
import com.cg.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OrderCreationParam {
    private LocationRegionParam locationRegion;
    private List<OrderItemParam> items;
    private OrderStatus status;
}
