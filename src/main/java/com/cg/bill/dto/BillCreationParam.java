package com.cg.bill.dto;

import com.cg.location.dto.LocationRegionParam;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class BillCreationParam {
    private Long userId;
    private LocationRegionParam locationRegion;
}
