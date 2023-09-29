package com.cg.bill;

import com.cg.bill.dto.BillResult;
import com.cg.model.Bill;
import com.cg.model.EPayment;

import java.util.List;
import java.util.Optional;

public interface IBillService {
    void delete(Bill bill);

    void deleteById(Long id);


    List<BillResult> findAllByStatus(EPayment ePayment);

//    List<BillResult> findBillByDate();

//    List<BillResult> findAllBillDTOORDER();
//
//
//    List<BillResult> findAllBillDTOLOADING();
//
//    List<BillResult> findAllBillDTOSHIPPING();

    List<BillResult> findAllByUserId(Long userId);


    List<BillResult> findBillDTOByIdBill(Long id);

    Bill findById(Long id);

    BillResult getById(Long id);


    List<BillResult> findAll();

}