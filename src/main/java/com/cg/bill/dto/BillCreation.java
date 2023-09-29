package com.cg.bill.dto;

import com.cg.dto.locationRegion.LocationRegionDTO;
import com.cg.model.EPayment;
import com.cg.model.LocationRegion;
import com.cg.model.User;
import com.cg.user.UserMapper;
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
public class BillCreation {
    private Long id;
    private BigDecimal totalAmount;
    private UserResult userDTO;
    private LocationRegionDTO locationRegionDTO;
    private Date createAt;
    private EPayment status;

    public BillCreation(Long id, BigDecimal totalAmount, UserResult user, LocationRegion locationRegion, Date createAt, EPayment status) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.userDTO = user.getFullName();
        this.locationRegionDTO = locationRegion.toLocationRegionDTO();
        this.createAt = createAt;
        this.status = status;
    }
}
