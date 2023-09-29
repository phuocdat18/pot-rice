package com.cg.bill;

import com.cg.model.Bill;
import com.cg.bill.dto.BillCreation;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IBillService extends IGeneralService<Bill, Long> {
    List<BillCreation> findAllBillDTO ();

    List<BillCreation> findBillByDate();

    List<BillCreation> findAllBillDTOORDER();


    List<BillCreation> findAllBillDTOLOADING();

    List<BillCreation> findAllBillDTOSHIPPING();

    List<BillCreation> findBillDTOByIdUser (Long id);


    List<BillCreation> findBillDTOByIdBill(Long id);
}