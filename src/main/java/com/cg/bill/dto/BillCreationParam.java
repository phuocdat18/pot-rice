package com.cg.bill.dto;

import com.cg.location.dto.LocationRegionDTO;
import com.cg.model.EPayment;
import com.cg.model.LocationRegion;
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
public class BillCreationParam {
    private Long id;
    private BigDecimal totalAmount;
    private UserResult userResult;
    private LocationRegionDTO locationRegionDTO;
    private Date createAt;
    private EPayment status;

    public BillCreationParam(Long id, BigDecimal totalAmount, UserResult userResult, LocationRegion locationRegion, Date createAt, EPayment status) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.userResult = userResult;
        this.locationRegionDTO = locationRegion.toLocationRegionDTO();
        this.createAt = createAt;
        this.status = status;
    }
}
