package com.cg.bill;

import com.cg.model.BillDetail;
import com.cg.bill.dto.BillDetailResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Long> {

    @Query("SELECT  " +
            "bd.id, " +
            "bd.product, " +
            "bd.title, " +
            "bd.unit, " +
            "bd.price, " +
            "bd.quantity, " +
            "bd.amount, " +
            "bd.bill " +
            "FROM BillDetail AS bd " +
            "JOIN Bill b ON bd.bill.id = b.id " +
            "WHERE b.user.id = :id"
    )
    List<BillDetailResult> findAllBillDetailDTO(Long id);
    @Query("SELECT " +
            "bd.id, " +
            "bd.product, " +
            "bd.title, " +
            "bd.unit, " +
            "bd.price, " +
            "bd.quantity, " +
            "bd.amount, " +
            "bd.bill " +
            "FROM BillDetail AS bd " +
            "WHERE bd.bill.id = :id "
    )
    List<BillDetailResult> findBillDetailByBillId (Long id);

    @Query("SELECT " +
            "bd.id, " +
            "bd.product, " +
            "bd.title, " +
            "bd.unit, " +
            "bd.price, " +
            "bd.quantity, " +
            "bd.amount, " +
            "bd.bill, " +
            "b.status" +
            "FROM BillDetail AS bd " +
            "JOIN Bill AS b ON bd.bill.id = b.id " +
            "WHERE bd.bill.id = :id "
    )
    List<BillDetailResult> findBillDetailByBillIdStatus (Long id);
}