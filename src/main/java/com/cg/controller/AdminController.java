package com.cg.controller;

import com.cg.model.UserPrincipal;
import com.cg.order.IOrderService;
import com.cg.user.IUserService;
import com.cg.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class AdminController {
    private final IOrderService orderService;


    @GetMapping
    public String showPageAdmin(Model model, @AuthenticationPrincipal UserPrincipal principal) {
        String roleCode = principal.getAuthorities().get(0).getAuthority();

        model.addAttribute("username", principal.getUsername());
        model.addAttribute("roleCode", roleCode);
        model.addAttribute("active", "dashboard");
        return "dashboard_admin/dashboard";
    }

    @GetMapping("/products")
    public String showListProduct(Model model, @AuthenticationPrincipal UserPrincipal principal) {
        String roleCode = principal.getAuthorities().get(0).getAuthority();
        model.addAttribute("username", principal.getUsername());
        model.addAttribute("roleCode", roleCode);
        model.addAttribute("active", "products");
        return "dashboard_admin/dashboard";
    }

    @GetMapping("/productsJob")
    public String showListProductJob(Model model, @AuthenticationPrincipal UserPrincipal principal) {
        List<?> billDTOS = orderService.findAllByUserId(principal.getId());
        String roleCode = principal.getAuthorities().get(0).getAuthority();
        model.addAttribute("username", principal.getUsername());
        model.addAttribute("roleCode", roleCode);
        model.addAttribute("bill", billDTOS);
        return "dashboard_admin/orderJob";
    }

    @GetMapping("/customers")
    public String showListCustomer(Model model, @AuthenticationPrincipal UserPrincipal principal) {
        String roleCode = principal.getAuthorities().get(0).getAuthority();
        model.addAttribute("username", principal.getUsername());
        model.addAttribute("roleCode", roleCode);
        model.addAttribute("active", "customers");
        return "dashboard_admin/list-user";
    }

    @GetMapping("/revenue")
    public String showRevenue(Model model, @AuthenticationPrincipal UserPrincipal principal) {
        String roleCode = principal.getAuthorities().get(0).getAuthority();
        model.addAttribute("username", principal.getUsername());
        model.addAttribute("roleCode", roleCode);
        model.addAttribute("active", "revenue");
        return "dashboard_admin/revenue";
    }
}
