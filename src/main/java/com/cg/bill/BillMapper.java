package com.cg.bill;

import com.cg.bill.dto.BillCreationParam;
import com.cg.bill.dto.BillResult;
import com.cg.model.Bill;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BillMapper {
    public Bill toEntity(BillCreationParam creationParam) {
        return new Bill()
                .setUserId(creationParam.getUserId())
                .setLocationRegionId(creationParam.getLocationRegion().getId())
                ;
    }


    public BillResult toDTO(Bill entity) {
        return new BillResult();

    }

    public List<BillResult> toDTOList(List<Bill> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }


}
