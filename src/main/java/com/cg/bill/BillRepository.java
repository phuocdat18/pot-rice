package com.cg.bill;

import com.cg.bill.dto.BillCreationParam;
import com.cg.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    @Query("SELECT " +
            "b.id, " +
            "b.totalAmount, " +
            "b.user," +
            "b.locationRegion," +
            "b.createdAt, " +
            "b.status " +
            "FROM Bill b " +
            "WHERE b.status = 0")
    List<BillCreationParam> findAllBillDTOORDER ();

    @Query("SELECT " +
            "b.id, " +
            "b.totalAmount, " +
            "b.user," +
            "b.locationRegion," +
            "b.createdAt, " +
            "b.status " +
            "FROM Bill b " +
            "WHERE b.status = 1")
    List<BillCreationParam> findAllBillDTOLOADING ();

    @Query("SELECT " +
            "b.id, " +
            "b.totalAmount, " +
            "b.user," +
            "b.locationRegion," +
            "b.createdAt, " +
            "b.status " +
            "FROM Bill b " +
            "WHERE b.status = 2")
    List<BillCreationParam> findAllBillDTOSHIPPING ();

    @Query("SELECT " +
            "b.id, " +
            "b.totalAmount, " +
            "b.user," +
            "b.locationRegion," +
            "b.createdAt, " +
            "b.status " +
            "FROM Bill b " +
            "WHERE b.status = 3")
    List<BillCreationParam> findAllBillDTO ();
    @Query("SELECT " +
            "b.id, " +
            "b.totalAmount, " +
            "b.user," +
            "b.locationRegion," +
            "b.createdAt, " +
            "b.status " +
            "FROM Bill b " +
            "WHERE b.status = 3" +
            "AND b.createdAt = toDate(CURRENT_DATE)")
    List<BillCreationParam> findAllBillByDate ();

    @Query("SELECT " +
            "b.id, " +
            "b.totalAmount, " +
            "b.user," +
            "b.locationRegion, " +
            "b.createdAt, " +
            "b.status " +
            "FROM Bill b " +
            "WHERE b.user.id = :id")
    List<BillCreationParam> findBillDTOByIdUser (Long id);

    @Query("SELECT " +
            "b.id, " +
            "b.totalAmount, " +
            "b.user," +
            "b.locationRegion, " +
            "b.createdAt, " +
            "b.status " +
            "FROM Bill b " +
            "WHERE b.id = :id")
    List<BillCreationParam> findBillDTOByIdBill (Long id);

}