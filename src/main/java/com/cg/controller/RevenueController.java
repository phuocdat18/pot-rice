package com.cg.controller;

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
@RequestMapping("/revenue")
public class RevenueController {

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private IUserService userService;
    @GetMapping
    public String showPageRevenue(Model model, @AuthenticationPrincipal UserPrincipal principal) {
        String roleCode = principal.getAuthorities().get(0).getAuthority();
        model.addAttribute("username", principal.getUsername());
        model.addAttribute("roleCode", roleCode);
        model.addAttribute("active", "dashboard");
        return "dashboard_admin/revenue";
    }


}
