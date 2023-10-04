package com.cg.controller;

import com.cg.cart.ICartService;
import com.cg.cart.dto.CartResult;
import com.cg.model.*;
import com.cg.order.IOrderService;
import com.cg.product.IProductService;
import com.cg.user.IUserService;
import com.cg.user.dto.UserResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class CustomerController {
    private final IUserService userService;

    private final IProductService productService;
    private final IOrderService orderService;
    private final ICartService cartService;


    @GetMapping
    public String showPageHome(Model model, @CookieValue(value = "cartId", required = false) Long cartId, HttpServletResponse response, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (cartId == null) {
            CartResult cart = cartService.newCart(userPrincipal.getId());
            Cookie cookie = new Cookie("cartId", cart.getId().toString());
            response.addCookie(cookie);
        }
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
        UserResult user = userService.getById(userPrincipal.getId());
        String roleCode = userPrincipal.getAuthorities().get(0).getAuthority();

        model.addAttribute("username", userPrincipal.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("roleCode", roleCode);
        return "user_info/user_myaccount";
    }

    @GetMapping("/my-order")
    public String showUserOrder(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        UserResult user = userService.getById(userPrincipal.getId());
        String roleCode = userPrincipal.getAuthorities().get(0).getAuthority();

        model.addAttribute("username", userPrincipal.getUsername());
        model.addAttribute("roleCode", roleCode);
        model.addAttribute("user", user);
        return "user_info/user_order";
    }

    @GetMapping("/my-order-detail")
    public String showUserOrderDetail(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        UserResult user = userService.getById(userPrincipal.getId());
        List<?> orderResults = orderService.findAllByUserId(userPrincipal.getId());

        String roleCode = userPrincipal.getAuthorities().get(0).getAuthority();

        model.addAttribute("username", userPrincipal.getUsername());
        model.addAttribute("roleCode", roleCode);
        model.addAttribute("user", user);
        model.addAttribute("order", orderResults);

        return "user_info/user_order_detail";
    }


}
