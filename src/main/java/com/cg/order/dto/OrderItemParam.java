package com.cg.order.dto;

import com.cg.location.dto.LocationRegionParam;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class OrderItemParam {
    private Long productId;
    @Size(min = 1, message = "")
    private Long quantity;
    private String unit;
}
