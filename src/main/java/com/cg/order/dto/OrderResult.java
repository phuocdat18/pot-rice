package com.cg.order.dto;

import com.cg.location.dto.LocationRegionParam;
import com.cg.model.OrderStatus;
import com.cg.user.dto.UserResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class OrderResult {
    private Long id;
    private BigDecimal totalAmount;
    private UserResult userResult;
    private LocationRegionParam locationRegionParam;
    private Date createAt;
    private OrderStatus status;
}
