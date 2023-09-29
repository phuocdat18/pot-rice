package com.cg.bill;


import com.cg.bill.dto.BillCreation;
import com.cg.model.Bill;
import com.cg.model.LocationRegion;
import com.cg.location.LocationRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BillServiceImpl implements IBillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private LocationRegionRepository locationRegionRepository;

    @Override
    public List<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public Optional<Bill> findById(Long id) {
        return billRepository.findById(id);
    }

    @Override
    public Bill save(Bill bill) {
        LocationRegion locationRegion = locationRegionRepository.save(bill.getLocationRegion());
        bill.setLocationRegion(locationRegion);
        return billRepository.save(bill);
    }

    @Override
    public void delete(Bill bill) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<BillCreation> findAllBillDTO() {
        return billRepository.findAllBillDTO();
    }

    @Override
    public List<BillCreation> findBillByDate() {
        return billRepository.findAllBillByDate();
    }

    @Override
    public List<BillCreation> findAllBillDTOORDER() {
        return billRepository.findAllBillDTOORDER();
    }

    @Override
    public List<BillCreation> findAllBillDTOLOADING() {
        return billRepository.findAllBillDTOLOADING();
    }
    @Override
    public List<BillCreation> findAllBillDTOSHIPPING() {
        return billRepository.findAllBillDTOSHIPPING();
    }


    @Override
    public List<BillCreation> findBillDTOByIdUser(Long id) {
        return billRepository.findBillDTOByIdUser(id);
    }

    @Override
    public List<BillCreation> findBillDTOByIdBill(Long id) {
        return billRepository.findBillDTOByIdBill(id);
    }

}