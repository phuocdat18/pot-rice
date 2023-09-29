package com.cg.bill;

import com.cg.model.Bill;
import com.cg.bill.dto.BillCreation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    @Query("SELECT NEW com.cg.model.dto.bill.BillDTO(" +
            "b.id, " +
            "b.totalAmount, " +
            "b.user," +
            "b.locationRegion," +
            "b.createdAt, " +
            "b.status) " +
            "FROM Bill b " +
            "WHERE b.status = 0")
    List<BillCreation> findAllBillDTOORDER ();

    @Query("SELECT NEW com.cg.model.dto.bill.BillDTO(" +
            "b.id, " +
            "b.totalAmount, " +
            "b.user," +
            "b.locationRegion," +
            "b.createdAt, " +
            "b.status) " +
            "FROM Bill b " +
            "WHERE b.status = 1")
    List<BillCreation> findAllBillDTOLOADING ();

    @Query("SELECT NEW com.cg.model.dto.bill.BillDTO(" +
            "b.id, " +
            "b.totalAmount, " +
            "b.user," +
            "b.locationRegion," +
            "b.createdAt, " +
            "b.status) " +
            "FROM Bill b " +
            "WHERE b.status = 2")
    List<BillCreation> findAllBillDTOSHIPPING ();

    @Query("SELECT NEW com.cg.model.dto.bill.BillDTO(" +
            "b.id, " +
            "b.totalAmount, " +
            "b.user," +
            "b.locationRegion," +
            "b.createdAt, " +
            "b.status) " +
            "FROM Bill b " +
            "WHERE b.status = 3")
    List<BillCreation> findAllBillDTO ();
    @Query("SELECT NEW com.cg.model.dto.bill.BillDTO(" +
            "b.id, " +
            "b.totalAmount, " +
            "b.user," +
            "b.locationRegion," +
            "b.createdAt, " +
            "b.status) " +
            "FROM Bill b " +
            "WHERE b.status = 3" +
            "AND b.createdAt = toDate(CURRENT_DATE)")
    List<BillCreation> findAllBillByDate ();

    @Query("SELECT NEW com.cg.model.dto.bill.BillDTO(" +
            "b.id, " +
            "b.totalAmount, " +
            "b.user," +
            "b.locationRegion, " +
            "b.createdAt, " +
            "b.status) " +
            "FROM Bill b " +
            "WHERE b.user.id = :id")
    List<BillCreation> findBillDTOByIdUser (Long id);

    @Query("SELECT NEW com.cg.model.dto.bill.BillDTO(" +
            "b.id, " +
            "b.totalAmount, " +
            "b.user," +
            "b.locationRegion, " +
            "b.createdAt, " +
            "b.status) " +
            "FROM Bill b " +
            "WHERE b.id = :id")
    List<BillCreation> findBillDTOByIdBill (Long id);

}