package com.cg.controller;

import com.cg.model.UserPrincipal;
import com.cg.user.IUserService;
import com.cg.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/revenue")
@RequiredArgsConstructor
public class RevenueController {
    private final AppUtils appUtils;
    private final IUserService userService;
    @GetMapping
    public String showPageRevenue(Model model, @AuthenticationPrincipal UserPrincipal principal) {
        String roleCode = principal.getAuthorities().get(0).getAuthority();
        model.addAttribute("username", principal.getUsername());
        model.addAttribute("roleCode", roleCode);
        model.addAttribute("active", "dashboard");
        return "dashboard_admin/revenue";
    }

}
