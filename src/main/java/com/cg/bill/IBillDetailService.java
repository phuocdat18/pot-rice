package com.cg.bill;

import com.cg.model.BillDetail;
import com.cg.model.CartDetail;
import com.cg.bill.dto.BillDetailResult;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IBillDetailService extends IGeneralService<BillDetail, Long> {

    BillDetail addBillDetail(BillDetail billDetail, CartDetail cartDetail);
    List<BillDetailResult> findAllBillDetailDTO(Long id);
    List<BillDetailResult> findBillDetailByBillId (Long id);

    List<BillDetailResult> findBillDetailByBillIdStatus(Long id);
}