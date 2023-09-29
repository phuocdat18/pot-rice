package com.cg.bill;

import com.cg.model.Bill;
import com.cg.bill.dto.BillCreationParam;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IBillService extends IGeneralService<Bill, Long> {
    List<BillCreationParam> findAllBillDTO ();

    List<BillCreationParam> findBillByDate();

    List<BillCreationParam> findAllBillDTOORDER();


    List<BillCreationParam> findAllBillDTOLOADING();

    List<BillCreationParam> findAllBillDTOSHIPPING();

    List<BillCreationParam> findBillDTOByIdUser (Long id);


    List<BillCreationParam> findBillDTOByIdBill(Long id);
}