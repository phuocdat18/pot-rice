package com.cg.bill;


import com.cg.bill.dto.BillCreationParam;
import com.cg.bill.dto.BillDetailDTO;
import com.cg.exception.DataInputException;
import com.cg.model.User;
import com.cg.user.IUserService;

import com.cg.product.service.IProductService;

import com.cg.utils.AppUtils;
import com.cg.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bills")
public class BillAPI {

    @Autowired
    private IBillService billService;

    @Autowired
    private IBillDetailService billDetailService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IProductService productService;

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private ValidateUtils validateUtils;

    @GetMapping()
    public ResponseEntity<List<?>> findAllBills() {
        try {
            List<BillCreationParam> billDTOS = billService.findAllBillDTO();

            if (billDTOS.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(billDTOS, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/order")
    public ResponseEntity<List<?>> findAllBillsORDER() {
        try {
            List<BillCreationParam> billDTOS = billService.findAllBillDTOORDER();

            if (billDTOS.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(billDTOS, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/loading")
    public ResponseEntity<List<?>> findAllBillsLOADING() {
        try {
            List<BillCreationParam> billDTOS = billService.findAllBillDTOLOADING();

            if (billDTOS.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(billDTOS, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/shipping")
    public ResponseEntity<List<?>> findAllBillsLOADINGSHIPPING() {
        try {
            List<BillCreationParam> billDTOS = billService.findAllBillDTOSHIPPING();

            if (billDTOS.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(billDTOS, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/bill-detail-by-bill/{id}")
    public ResponseEntity<List<?>> findAllBillDetail (@PathVariable String id) {
        if (!validateUtils.isNumberValid(id)) {
            throw new DataInputException("Mã bill không hợp lệ");
        }
        Long billId = Long.parseLong(id);

        try {
            List<BillDetailDTO> billDetailDTOS = billDetailService.findBillDetailByBillIdStatus(billId);

            if (billDetailDTOS.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(billDetailDTOS, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("/bill-info/{id}")
    public ResponseEntity<List<?>> findBillDTOByIdBill (@PathVariable String id) {
        if (!validateUtils.isNumberValid(id)) {
            throw new DataInputException("Mã bill không hợp lệ");
        }
        Long billId = Long.parseLong(id);

        try {
            List<BillCreationParam> billDTOS = billService.findBillDTOByIdBill(billId);

            if (billDTOS.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(billDTOS, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }




    @GetMapping("/bill-detail-by-user")
    public ResponseEntity<List<?>> findAllBillDetailByUser() {

        String username = appUtils.getPrincipalUsername();

        Optional<User> userOptional = userService.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new DataInputException("User not valid");
        }

        try {
            List<BillCreationParam> billDTOS = billService.findBillDTOByIdUser(userOptional.get().getId());

            if (billDTOS.isEmpty()) {
                throw new DataInputException("Bill not valid");
            }

            List<BillDetailDTO> billDetailDTOS = billDetailService.findAllBillDetailDTO(userOptional.get().getId());


            if (billDetailDTOS.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(billDetailDTOS, HttpStatus.OK);

        } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<?>> findAllBillByIdUser(@PathVariable String id) {
        if (!validateUtils.isNumberValid(id)) {
            throw new DataInputException("Mã sản phẩm không hợp lệ");
        }
        Long userId = Long.parseLong(id);

        try {
            List<BillCreationParam> billDTOS = billService.findBillDTOByIdUser(userId);

            if (billDTOS.isEmpty()) {
                throw new DataInputException("Bill not valid");
            }

            if (billDTOS.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(billDTOS, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}