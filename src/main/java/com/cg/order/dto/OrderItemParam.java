package com.cg.order.dto;

import com.cg.location.dto.LocationRegionParam;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class OrderItemParam {
    private Long productId;
    private String quantity;
    private String unit;
}
