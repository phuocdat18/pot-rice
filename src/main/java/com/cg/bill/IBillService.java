package com.cg.bill;

import com.cg.model.Bill;
import com.cg.bill.dto.BillDTO;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IBillService extends IGeneralService<Bill, Long> {
    List<BillDTO> findAllBillDTO ();

    List<BillDTO> findBillByDate();

    List<BillDTO> findAllBillDTOORDER();


    List<BillDTO> findAllBillDTOLOADING();

    List<BillDTO> findAllBillDTOSHIPPING();

    List<BillDTO> findBillDTOByIdUser (Long id);


    List<BillDTO> findBillDTOByIdBill(Long id);
}