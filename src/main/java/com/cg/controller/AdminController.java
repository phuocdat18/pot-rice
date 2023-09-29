package com.cg.controller;

import com.cg.bill.IBillDetailService;
import com.cg.bill.IBillService;
import com.cg.bill.dto.BillResult;
import com.cg.exception.DataInputException;
import com.cg.model.Role;
import com.cg.model.User;
import com.cg.model.UserPrincipal;
import com.cg.user.IUserService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/dashboard")
public class AdminController {
    @Autowired
    private AppUtils appUtils;

    @Autowired
    private IUserService userService;
    @Autowired
    private IBillService billService;
    @Autowired
    private IBillDetailService billDetailService;

    @GetMapping
    public String showPageAdmin(Model model) {
        String username = appUtils.getPrincipalUsername();

        Optional<User> userOptional = userService.findByUsername(username);

        if (!userOptional.isPresent()) {
            throw new DataInputException("User not valid");
        }

        Role role = userOptional.get().getRole();
        String roleCode = role.getCode().getValue();

//        username = username.substring(0, username.indexOf("@"));
        model.addAttribute("username", username);
        model.addAttribute("roleCode", roleCode);
        model.addAttribute("active", "dashboard");
        return "dashboard_admin/dashboard";
    }

    @GetMapping("/products")
    public String showListProduct(Model model) {
        String username = appUtils.getPrincipalUsername();

        Optional<User> userOptional = userService.findByUsername(username);

        if (!userOptional.isPresent()) {
            throw new DataInputException("User not valid");
        }

        Role role = userOptional.get().getRole();
        String roleCode = role.getCode().getValue();

//        username = username.substring(0, username.indexOf("@"));
        model.addAttribute("username", username);
        model.addAttribute("roleCode", roleCode);
        model.addAttribute("active", "products");
        return "dashboard_admin/dashboard";
    }

    @GetMapping("/productsJob")
    public String showListProductJob(Model model, @AuthenticationPrincipal UserPrincipal principal) {
//        String username = appUtils.getPrincipalUsername();
//
//        Optional<User> userOptional = userService.findByUsername(username);
//        Long userId = userOptional.get().getId();
//        List<BillResult> billDTOS = billService.findAllByUserId(userId);
////        List<BillDetailDTO> billDetailDTOS = billDetailService.findBillDetailByBillIdStatus(id);
//
//        if (!userOptional.isPresent()) {
//            throw new DataInputException("User not valid");
//        }
//
//        Role role = userOptional.get().getRole();
//        String roleCode = role.getCode();
//
////        username = username.substring(0, username.indexOf("@"));
//        model.addAttribute("username", username);
//        model.addAttribute("roleCode", roleCode);
//        model.addAttribute("bill",billDTOS);
////        model.addAttribute("billDetailDTOS", billDetailDTOS);
////        model.addAttribute("idBill", id);
//        return "dashboard_admin/orderJob";


        List<BillResult> billDTOS = billService.findAllByUserId(principal.getId());
//        List<BillDetailDTO> billDetailDTOS = billDetailService.findBillDetailByBillIdStatus(id);
        String roleCode = principal.getAuthorities().get(0).getAuthority();
        model.addAttribute("username", principal.getUsername());
        model.addAttribute("roleCode", roleCode);
        model.addAttribute("bill", billDTOS);
        ////        model.addAttribute("billDetailDTOS", billDetailDTOS);
////        model.addAttribute("idBill", id);
        return "dashboard_admin/orderJob";
    }

    @GetMapping("/customers")
    public String showListCustomer(Model model) {
        String username = appUtils.getPrincipalUsername();

        Optional<User> userOptional = userService.findByUsername(username);

        if (!userOptional.isPresent()) {
            throw new DataInputException("User not valid");
        }

        Role role = userOptional.get().getRole();
        String roleCode = role.getCode().getValue();

//        username = username.substring(0, username.indexOf("@"));
        model.addAttribute("username", username);
        model.addAttribute("roleCode", roleCode);
        model.addAttribute("active", "customers");
        return "dashboard_admin/list-user";
    }

    @GetMapping("/revenue")
    public String showRevenue(Model model) {
        String username = appUtils.getPrincipalUsername();

        Optional<User> userOptional = userService.findByUsername(username);

        if (!userOptional.isPresent()) {
            throw new DataInputException("User not valid");
        }

        Role role = userOptional.get().getRole();
        String roleCode = role.getCode().getValue();

//        username = username.substring(0, username.indexOf("@"));
        model.addAttribute("username", username);
        model.addAttribute("roleCode", roleCode);
        model.addAttribute("active", "revenue");
        return "dashboard_admin/revenue";
    }
}
