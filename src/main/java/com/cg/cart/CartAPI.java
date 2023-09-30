package com.cg.cart;

import com.cg.cart.dto.CartUpdateParam;
import com.cg.cartDetail.ICartDetailService;
import com.cg.cartDetail.dto.CartDetailChangeReqDTO;
import com.cg.cartDetail.dto.CartDetailResult;
import com.cg.exception.DataInputException;
import com.cg.model.*;
import com.cg.order.IOrderItemService;
import com.cg.order.IOrderService;
import com.cg.order.dto.OrderCreationParam;
import com.cg.product.service.IProductService;
import com.cg.user.IUserService;
import com.cg.utils.AppUtils;
import com.cg.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartAPI {

    @Autowired
    private ICartService cartService;

    @Autowired
    private ICartDetailService cartDetailService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderItemService orderItemService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IProductService productService;

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private ValidateUtils validateUtils;

    @GetMapping
    public ResponseEntity<List<?>> findAllCartDetail() {

        String username = appUtils.getPrincipalUsername();

        List<User> userOptional = userService.findUserByUsername(username);

        try {
            List<CartDetailResult> cartDetailResults = cartDetailService.findAllCartDetailDTO(userOptional.get().getId());

            if (cartDetailResults.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(cartDetailResults, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity<List<?>> addToCart(@RequestBody CartUpdateParam cartUpdateParam) {

        String username = appUtils.getPrincipalUsername();

        List<User> userOptional = userService.findUserByUsername(username);

        Long productId = cartUpdateParam.getProductId();
        Optional<Product> productOptional = Optional.ofNullable(productService.findById(productId));

        if (productOptional.isEmpty()) {
            throw new DataInputException("Product invalid");
        }
        if (productOptional.get().getQuantity() < cartUpdateParam.getQuantity()) {
            throw new DataInputException("Quantity invalid");
        }

        Product product = productOptional.get();

        Cart cart = cartService.addToCart(cartUpdateParam, product, userOptional.get());

        try {
            List<CartDetailResult> cartDetailResults = cartDetailService.findAllCartDetailDTO(userOptional.get().getId());

            if (cartDetailResults.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(cartDetailResults, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/payment")
    @PreAuthorize("#userId == principal.id")
    public ResponseEntity<?> payment(@Valid @RequestBody OrderCreationParam orderCreationParam) {
        List<CartDetail> cartDetails = cartDetailService.findAllByCartId();

        if (cartDetails.isEmpty()) {
            throw new DataInputException("CartDetail invalid");
        }
        for (CartDetail cartDetail : cartDetails) {
            Optional<Product> productOptional = Optional.ofNullable(productService.findById(cartDetail.getProduct().getId()));
            Product product = productOptional.get();
            if (product.getQuantity() < cartDetail.getQuantity()) {
                throw new DataInputException("Số lượng sản phẩm " + cartDetail.getId() + " không đủ!");
            }
            Long quantityNew = product.getQuantity() - cartDetail.getQuantity();
            product.setQuantity(quantityNew);
            productService.save(product);
        }
        BigDecimal vat = cartOptional.get().getTotalAmount().multiply(BigDecimal.valueOf(0.1));
        BigDecimal totalBill = cartOptional.get().getTotalAmount().add(vat).add(BigDecimal.valueOf(15000));

//        Bill bill = billService.save(new Bill(totalBill, principalId, billCreationParam.getLocationRegionReqDTO().toLocationRegion(null), EPayment.DONE));
//        for (CartDetail cartDetail : cartDetails) {
//            billDetailService.addBillDetail(new BillDetail(cartDetail.getProduct(), cartDetail.getTitle(), cartDetail.getUnit(), cartDetail.getPrice(), cartDetail.getQuantity(), cartDetail.getAmount(), bill), cartDetail);
//        }
        cartService.delete(cartOptional.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/payment")
    @PreAuthorize("#userId == principal.id")
    @ResponseStatus(HttpStatus.OK)
    public List<?> findAllByUserId(@PathVariable Long userId, String status) {
        if (status != null)
            return orderService.findAllByUserIdAndStatus(userId, status);
        return orderService.findAllByUserId(userId);
    }


    @PatchMapping("/bill/{id}")
    public ResponseEntity<?> updateBillStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
     Order order = orderService.findById(id);

//        if (billOptional.isEmpty()) {
//            throw new DataInputException("Bill not found with id: " + id);
//        }

//        bill.setStatus(EPayment.valueOf(request.get("status")));
//        billService.save(bill);

        return new ResponseEntity<>(HttpStatus.OK);
    }


//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<List<?>> delete(@PathVariable String id) throws IOException {
//        String username = appUtils.getPrincipalUsername();
//
//        List<User> userOptional = userService.findUserByUsername(username);
//
//        if (!validateUtils.isNumberValid(id)) {
//            throw new DataInputException("Sản phẩm không hợp lệ");
//        }
//        Long cartDetailId = Long.parseLong(id);
//
//        Optional<CartDetail> cartDetailOptional = cartDetailService.findById(cartDetailId);
//
//        if (cartDetailOptional.isPresent()) {
//            cartDetailService.delete(cartDetailOptional.get());
//        } else {
//            throw new DataInputException("Invalid product information");
//        }
//        try {
//            List<CartDetailResult> cartDetailResults = cartDetailService.findAllCartDetailDTO(userOptional.get().getId());
//
//            if (cartDetailResults.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(cartDetailResults, HttpStatus.OK);
//
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    @PatchMapping("/change-quantity/{id}")
    public ResponseEntity<List<?>> changeQuantity(@PathVariable String id, @RequestBody CartDetailChangeReqDTO cartDetailChangeReqDTO) throws IOException {
        String username = appUtils.getPrincipalUsername();

        List<User> userOptional = userService.findUserByUsername(username);
        if (!validateUtils.isNumberValid(id)) {
            throw new DataInputException("Cart detail không hợp lệ");
        }
        Long cartDetailId = Long.parseLong(id);

        Optional<CartDetail> cartDetailOptional = cartDetailService.findById(cartDetailId);

        CartDetail cartDetail = cartDetailOptional.get();
        BigDecimal newAmout = cartDetail.getPrice().multiply(BigDecimal.valueOf(cartDetailChangeReqDTO.getQuantity()));

        cartDetail.setQuantity(cartDetailChangeReqDTO.getQuantity());
        cartDetail.setAmount(newAmout);

        cartDetailService.create(cartDetail);

        try {
            List<CartDetailResult> cartDetailResults = cartDetailService.findAllCartDetailDTO(userOptional.get().getId());

            if (cartDetailResults.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(cartDetailResults, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        cartService.deleteById(id);
    }
}
