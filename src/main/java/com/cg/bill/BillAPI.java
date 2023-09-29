package com.cg.bill;


import com.cg.bill.dto.BillDetailResult;
import com.cg.exception.DataInputException;
import com.cg.model.EPayment;
import com.cg.model.User;
import com.cg.product.service.IProductService;
import com.cg.user.IUserService;
import com.cg.utils.AppUtils;
import com.cg.utils.ValidateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bills")
public class BillAPI {

    private final IBillService billService;
    private final IBillDetailService billDetailService;
    private final IUserService userService;
    private final IProductService productService;
    private final AppUtils appUtils;
    private final ValidateUtils validateUtils;

    @GetMapping
    public ResponseEntity<?> findAllBills() {
        try {
            List<?> billDTOS = billService.findAllByStatus(EPayment.DONE);

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
            List<?> billDTOS = billService.findAllByStatus(EPayment.ORDER);

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
            List<?> billDTOS = billService.findAllByStatus(EPayment.LOADING);

            if (billDTOS.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(billDTOS, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/shipping")
    public ResponseEntity<?> findAllBillsLOADINGSHIPPING() {
        try {
            List<?> billDTOS = billService.findAllByStatus(EPayment.SHIPPING);

            if (billDTOS.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(billDTOS, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/bill-detail-by-bill/{id}")
    public ResponseEntity<?> findAllBillDetail(@PathVariable String id) {
        if (!validateUtils.isNumberValid(id)) {
            throw new DataInputException("Mã bill không hợp lệ");
        }
        Long billId = Long.parseLong(id);

        try {
            List<BillDetailResult> billDetailDTOS = billDetailService.findBillDetailByBillIdStatus(billId);

            if (billDetailDTOS.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(billDetailDTOS, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/bill-info/{id}")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> findBillDTOByIdBill(@PathVariable String id) {
        if (!validateUtils.isNumberValid(id)) {
            throw new DataInputException("Mã bill không hợp lệ");
        }
        Long billId = Long.parseLong(id);

        try {
            List<?> billDTOS = billService.findBillDTOByIdBill(billId);

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
            List<?> billDTOS = billService.findAllByUserId(userOptional.get().getId());

            if (billDTOS.isEmpty()) {
                throw new DataInputException("Bill not valid");
            }

            List<BillDetailResult> billDetailDTOS = billDetailService.findAllBillDetailDTO(userOptional.get().getId());


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
            List<?> billDTOS = billService.findAllByUserId(userId);

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
