package com.cg.controller;

import com.cg.order.dto.OrderCreationParam;
import com.cg.exception.DataInputException;
import com.cg.model.*;
<<<<<<< HEAD

=======
>>>>>>> hoan-dev
import com.cg.order.IOrderService;
import com.cg.order.IOrderItemService;
import com.cg.order.dto.OrderItemResult;
import com.cg.user.IUserService;
import com.cg.product.service.IProductService;
<<<<<<< HEAD
=======
import com.cg.order.dto.BillDetailResult;
>>>>>>> hoan-dev
import com.cg.utils.AppUtils;
import com.cg.utils.ValidateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class CustomerController {

<<<<<<< HEAD
    @Autowired
    private AppUtils appUtils;

    @Autowired
    private IUserService userService;

    @Autowired
    private ValidateUtils validateUtils;

    @Autowired
    private IProductService productService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderItemService orderItemService;
=======
    private final AppUtils appUtils;
    private final IUserService userService;
    private final ValidateUtils validateUtils;
    private final IProductService productService;
    private final IOrderService billService;
    private final IOrderItemService billDetailService;
>>>>>>> hoan-dev

    @GetMapping
    public String showPageHome(Model model) {
        String username = appUtils.getPrincipalUsername();

        Optional<User> userOptional = userService.findByUsername(username);

        if (!userOptional.isPresent()) {
            throw new DataInputException("User not valid");
        }

        Role role = userOptional.get().getRole();
        String roleCode = String.valueOf(role.getCode());

//        username = username.substring(0, username.indexOf("@"));
        model.addAttribute("username", username);
        model.addAttribute("roleCode", roleCode);
        model.addAttribute("active", "shop");
        return "shop/home";
    }

    @GetMapping("/menu")
    public String showPageMenu(Model model) {
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
        model.addAttribute("active", "menu");
        return "shop/menu";
    }

    @GetMapping("/chefs")
    public String showChefs(Model model) {
        String username = appUtils.getPrincipalUsername();
        Optional<User> userOptional = userService.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new DataInputException("User not valid");
        }
        Role role = userOptional.get().getRole();
        String roleCode = role.getCode().getValue();


        model.addAttribute("username", username);

        model.addAttribute("user", userOptional.get());
        model.addAttribute("roleCode", roleCode);
        model.addAttribute("active", "chefs");
        return "shop/chefs";
    }

    @GetMapping("/product-detail/{id}")
    private String showProductDetail(@PathVariable Long id, Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
//        String username = appUtils.getPrincipalUsername();
//        Optional<User> userOptional = userService.findByUsername(username);
//        if (userOptional.isEmpty()) {
//            throw new DataInputException("User not valid");
//        }
//        Role role = userOptional.get().getRole();
//        String roleCode = String.valueOf(role.getCode());
//
//
//        if (!validateUtils.isNumberValid(id)) {
//            throw new DataInputException("Mã sản phẩm không hợp lệ");
//        }
//        Long productId = Long.parseLong(id);
//
//        Optional<Product> product = Optional.ofNullable(productService.findById(productId));
//
//        if (product.isEmpty()) {
//            throw new DataInputException("Không tìm thấy sản phẩm");
//        }
//
//        model.addAttribute("username", username);
//
//        model.addAttribute("user", userOptional.get());
//        model.addAttribute("roleCode", roleCode);
//        model.addAttribute("product", );

        Product product = productService.findById(id);
        String roleCode = userPrincipal.getAuthorities().get(0).getAuthority();

        model.addAttribute("username", userPrincipal.getUsername());
        model.addAttribute("roleCode", roleCode);
        model.addAttribute("product", product);

        return "shop/menu_detail";
    }


    @GetMapping("/cart")
    private String viewCart(Model model) {
        String username = appUtils.getPrincipalUsername();
        Optional<User> userOptional = userService.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new DataInputException("User not valid");
        }
        Role role = userOptional.get().getRole();
        String roleCode = String.valueOf(role.getCode());
        model.addAttribute("username", username);

        model.addAttribute("user", userOptional.get());
        model.addAttribute("roleCode", roleCode);

        return "shop/cart_view";
    }


    @GetMapping("/myaccount")
    public String showListProduct(Model model) {
        String username = appUtils.getPrincipalUsername();

        Optional<User> userOptional = userService.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new DataInputException("User not valid");
        }

        Role role = userOptional.get().getRole();
        String roleCode = String.valueOf(role.getCode());

        model.addAttribute("username", username);

        model.addAttribute("user", userOptional.get());
        model.addAttribute("roleCode", roleCode);
        return "user_info/user_myaccount";
    }

    @GetMapping("/editInfo")
    public String showUserInfoEdit(Model model) {
        String username = appUtils.getPrincipalUsername();

        Optional<User> userOptional = userService.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new DataInputException("User not valid");
        }

        Role role = userOptional.get().getRole();
        String roleCode = String.valueOf(role.getCode());

        model.addAttribute("username", username);
        model.addAttribute("user", userOptional.get());
        model.addAttribute("roleCode", roleCode);
        return "user_info/user_info_edit";
    }

    @GetMapping("/my-order")
    public String showUserOrder(Model model) {
        String username = appUtils.getPrincipalUsername();

        Optional<User> userOptional = userService.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new DataInputException("User not valid");
        }

        Role role = userOptional.get().getRole();
        String roleCode = String.valueOf(role.getCode());

        model.addAttribute("username", username);
        model.addAttribute("user", userOptional.get());
        model.addAttribute("roleCode", roleCode);
        return "user_info/user_order";
    }

    @GetMapping("/my-order-detail")
    public String showUserOrderDetail(Model model, @RequestParam long id, @AuthenticationPrincipal UserPrincipal userPrincipal) {
//        String username = appUtils.getPrincipalUsername();
//
//        Optional<User> userOptional = userService.findByUsername(username);
//        if (userOptional.isEmpty()) {
//            throw new DataInputException("User not valid");
//        }
//
//        User user = userOptional.get();
//        Long userId = user.getId();
//
//        List<OrderCreationParam> userBillDTOs = billService.findBillDTOByIdUser(userId);
//        List<BillCreation> userBillDTOs = billService.findAllByUserId(userId);
//        model.addAttribute("bill", userBillDTOs);
//
//        List<OrderCreationParam> billDTOsById = billService.findBillDTOByIdBill(id);
//        if (billDTOsById.isEmpty()) {
//            throw new DataInputException("Order not found");
//        }
//        model.addAttribute("billById", billDTOsById.get(0));
//
//        List<BillDetailResult> billDetailDTOS = billDetailService.findBillDetailByBillIdStatus(id);
//        model.addAttribute("billDetailDTOS", billDetailDTOS);


        String roleCode = userPrincipal.getAuthorities().get(0).getAuthority();

<<<<<<< HEAD
        List<OrderCreationParam> userBillDTOs = orderService.findBillDTOByIdUser(userId);

        model.addAttribute("bill", userBillDTOs);

        List<OrderCreationParam> billDTOsById = orderService.findBillDTOByIdBill(id);
        if (billDTOsById.isEmpty()) {
            throw new DataInputException("Order not found");
        }
        model.addAttribute("billById", billDTOsById.get(0));

        List<OrderItemResult> billDetailDTOS = orderItemService.findBillDetailByBillIdStatus(id);
        model.addAttribute("billDetailDTOS", billDetailDTOS);

        String roleCode = user.getRole().getCode().getValue();
        model.addAttribute("username", username);
        model.addAttribute("user", user);
=======
        model.addAttribute("username", userPrincipal.getUsername());
>>>>>>> hoan-dev
        model.addAttribute("roleCode", roleCode);
        model.addAttribute("idBill", id);

        return "user_info/user_order_detail";
    }


}
