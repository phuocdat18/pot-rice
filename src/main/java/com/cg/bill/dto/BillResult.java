package com.cg.bill.dto;

import com.cg.location.dto.LocationRegionParam;
import com.cg.model.EPayment;
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
public class BillResult {
    private Long id;
    private BigDecimal totalAmount;
    private UserResult userResult;
    private LocationRegionParam locationRegionParam;
    private Date createAt;
    private EPayment status;
}
