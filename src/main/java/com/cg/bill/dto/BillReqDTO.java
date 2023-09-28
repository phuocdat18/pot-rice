package com.cg.bill.dto;

import com.cg.model.EPayment;
import com.cg.model.dto.locationRegion.LocationRegionReqDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BillReqDTO {
    private LocationRegionReqDTO locationRegionReqDTO;
    private EPayment status;
}
