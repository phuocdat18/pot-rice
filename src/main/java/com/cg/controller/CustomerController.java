package com.cg.controller;

import com.cg.model.*;
import com.cg.order.IOrderService;
import com.cg.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class CustomerController {

    private final IProductService productService;
    private final IOrderService orderService;


    @GetMapping
    public String showPageHome(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
<<<<<<< HEAD
=======

>>>>>>> thi-dev
        String roleCode = userPrincipal.getAuthorities().get(0).getAuthority();

        model.addAttribute("username", userPrincipal.getUsername());
        model.addAttribute("roleCode", roleCode);
        model.addAttribute("active", "shop");
        return "shop/home";
    }

    @GetMapping("/menu")
    public String showPageMenu(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {

        String roleCode = userPrincipal.getAuthorities().get(0).getAuthority();

        model.addAttribute("username", userPrincipal.getUsername());
        model.addAttribute("roleCode", roleCode);
        model.addAttribute("active", "menu");
        return "shop/menu";
    }

    @GetMapping("/chefs")
    public String showChefs(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {

        String roleCode = userPrincipal.getAuthorities().get(0).getAuthority();

        model.addAttribute("username", userPrincipal.getUsername());
        model.addAttribute("roleCode", roleCode);
        model.addAttribute("active", "chefs");
        return "shop/chefs";
    }

    @GetMapping("/product-detail/{id}")
    private String showProductDetail(@PathVariable Long id, Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {

        Product product = productService.findById(id);
        String roleCode = userPrincipal.getAuthorities().get(0).getAuthority();

        model.addAttribute("username", userPrincipal.getUsername());
        model.addAttribute("roleCode", roleCode);
        model.addAttribute("product", product);

        return "shop/menu_detail";
    }


    @GetMapping("/cart")
    private String viewCart(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {

        String roleCode = userPrincipal.getAuthorities().get(0).getAuthority();
        model.addAttribute("username", userPrincipal.getUsername());
        model.addAttribute("roleCode", roleCode);

        return "shop/cart_view";
    }


    @GetMapping("/myaccount")
    public String showListProduct(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {

        String roleCode = userPrincipal.getAuthorities().get(0).getAuthority();

        model.addAttribute("username", userPrincipal.getUsername());
        model.addAttribute("roleCode", roleCode);
        return "user_info/user_myaccount";
    }

    @GetMapping("/editInfo")
    public String showUserInfoEdit(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        String roleCode = userPrincipal.getAuthorities().get(0).getAuthority();

        model.addAttribute("username", userPrincipal.getUsername());
        model.addAttribute("roleCode", roleCode);
        return "user_info/user_info_edit";
    }

    @GetMapping("/my-order")
    public String showUserOrder(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {

        String roleCode = userPrincipal.getAuthorities().get(0).getAuthority();

        model.addAttribute("username", userPrincipal.getUsername());
        model.addAttribute("roleCode", roleCode);
        return "user_info/user_order";
    }

    @GetMapping("/my-order-detail")
    public String showUserOrderDetail(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {

        List<?> orderResults = orderService.findAllByUserId(userPrincipal.getId());

        String roleCode = userPrincipal.getAuthorities().get(0).getAuthority();

        model.addAttribute("username", userPrincipal.getUsername());
        model.addAttribute("roleCode", roleCode);
        model.addAttribute("order", orderResults);

        return "user_info/user_order_detail";
    }


}
