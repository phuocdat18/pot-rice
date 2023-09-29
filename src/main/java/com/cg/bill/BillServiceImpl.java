package com.cg.bill;

import com.cg.bill.dto.BillResult;
import com.cg.exception.DataInputException;
import com.cg.model.Bill;
import com.cg.model.EPayment;
import com.cg.model.LocationRegion;
import com.cg.location.LocationRegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BillServiceImpl implements IBillService {

    private final BillRepository billRepository;

    private final LocationRegionRepository locationRegionRepository;
    private final BillMapper billMapper;

    @Override
    public List<BillResult> findAll() {
        List<Bill> entities = billRepository.findAll();
        return billMapper.toDTOList(entities);
    }

    @Override
    public Bill findById(Long id) {
        return billRepository.findById(id)
                .orElseThrow(() -> new DataInputException("id ko hop le"));
    }

    @Override
    public BillResult getById(Long id) {
        Bill entity = findById(id);
        return billMapper.toDTO(entity);

    }

//    @Override
//    public Bill save(Bill bill) {
//        LocationRegion locationRegion = locationRegionRepository.save(bill.getLocationRegion());
//        bill.setLocationRegion(locationRegion);
//        return billRepository.save(bill);
//    }

    @Override
    public void delete(Bill bill) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<BillResult> findAllByStatus(EPayment ePayment) {
        List<Bill> entities = billRepository.findAllByStatus(EPayment.DONE);
        return billMapper.toDTOList(entities);
    }

//    @Override
//    public List<BillResult> findBillByDate() {
//        return billRepository.findAllBillByDate();
//    }

//    @Override
//    public List<BillResult> findAllBillDTOORDER() {
//        return billRepository.findAllBillDTOORDER();
//    }
//
//    @Override
//    public List<BillResult> findAllBillDTOLOADING() {
//        return billRepository.findAllBillDTOLOADING();
//    }
//
//    @Override
//    public List<BillResult> findAllBillDTOSHIPPING() {
//        return billRepository.findAllBillDTOSHIPPING();
//    }


    @Override
    public List<BillResult> findAllByUserId(Long userId) {
        List<Bill> entities = billRepository.findAllByUserId(userId);
        return billMapper.toDTOList(entities);
    }

    @Override
    public List<BillResult> findBillDTOByIdBill(Long id) {
        return null;
    }


//    @Override
//    public List<BillResult> findBillDTOByIdBill(Long id) {
//        return billRepository.findBillDTOByIdBill(id);
//    }

}