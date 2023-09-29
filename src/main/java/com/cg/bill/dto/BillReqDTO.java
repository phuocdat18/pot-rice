package com.cg.bill.dto;

import com.cg.location.dto.LocationRegionReqDTO;
import com.cg.model.EPayment;
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
