package com.cg.cart;

import com.cg.cartItem.ICartDetailService;
import com.cg.cartItem.dto.CartDetailResult;
import com.cg.cartItem.dto.CartDetailUpdateParam;
import com.cg.exception.DataInputException;
import com.cg.model.*;
import com.cg.order.IOrderItemService;
import com.cg.order.IOrderService;
import com.cg.order.dto.OrderCreationParam;
import com.cg.product.IProductService;
import com.cg.user.IUserService;
import com.cg.utils.AppUtils;
import com.cg.utils.ValidateUtils;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/api/carts")
public class CartAPI {

    private final ICartService cartService;
    private final ICartDetailService cartDetailService;
    private final IOrderService orderService;
    private final IOrderItemService orderItemService;
    private final IUserService userService;
    private final IProductService productService;
    private final AppUtils appUtils;
    private final ValidateUtils validateUtils;


    @GetMapping("/{userId}/cart/{cartId}")
    @PreAuthorize("#userId == principal.id")
    @ResponseStatus(HttpStatus.OK)
    public List<?> findAllCartDetail(@PathVariable Long userId, Long cartId) {
//            List<CartDetailResult> cartDetailResults = cartDetailService.findAllByCartId(cartId);
//            List<CartResult> cartDetailResults = cartService.findAllByUserIdAndCartId(userId, cartId);
//            if (cartDetailResults.isEmpty()) {
//            }
        return cartService.findAllByUserIdAndCartId(userId, cartId);
    }

//    @GetMapping
//    public ResponseEntity<List<?>> findAllCartDetail() {
//
//        String username = appUtils.getPrincipalUsername();
//
//        List<User> userOptional = userService.findUserByUsername(username);
//
//        try {
//            List<CartDetailResult> cartDetailResults = cartDetailService.findAllByCartId(cartId);
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

    @PostMapping("/{userId}/add-to-cart")
    @PreAuthorize(("#userId == principal"))

    @PostMapping("/add-to-cart")
    public ResponseEntity<List<?>> addToCart(@RequestBody CartDetailUpdateParam cartDetailUpdateParam) {

        String username = appUtils.getPrincipalUsername();

        List<User> userOptional = userService.findUserByUsername(username);

        Long productId = cartDetailUpdateParam.getProductId();
        Optional<Product> productOptional = Optional.ofNullable(productService.findById(productId));

        if (productOptional.isEmpty()) {
            throw new DataInputException("Product invalid");
        }
        if (productOptional.get().getQuantity() < cartDetailUpdateParam.getQuantity()) {
            throw new DataInputException("Quantity invalid");
        }

        Product product = productOptional.get();

        Cart cart = cartService.addToCart(cartDetailUpdateParam, product, userOptional.get());

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
    public ResponseEntity<?> payment(@Valid @RequestBody OrderCreationParam orderCreationParam, @AuthenticationPrincipal UserPrincipal principal) {
//        String username = appUtils.getPrincipalUsername();

//        Optional<User> userOptional = userService.findByUsername(principal.getUsername());
        Long principalId = principal.getId();
        Optional<Cart> cartOptional = cartService.findByUserId(principalId);


        Cart cart = cartOptional.orElseThrow(() -> new DataInputException("Cart invalid"));


        List<CartDetail> cartDetails = cartDetailService.findCartDetailsByCartId(cart.getId());

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


    @PatchMapping("/order/{id}")
    public ResponseEntity<?> updateBillStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
     Order billOptional = billService.findById(id);

//        if (billOptional.isEmpty()) {
//            throw new DataInputException("Bill not found with id: " + id);
//        }

//        bill.setStatus(EPayment.valueOf(request.get("status")));
//        billService.save(bill);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<?>> delete(@PathVariable String id) throws IOException {
        String username = appUtils.getPrincipalUsername();

        List<User> userOptional = userService.findUserByUsername(username);

        if (!validateUtils.isNumberValid(id)) {
            throw new DataInputException("Sản phẩm không hợp lệ");
        }
        Long cartDetailId = Long.parseLong(id);

        Optional<CartDetail> cartDetailOptional = cartDetailService.findById(cartDetailId);

        if (cartDetailOptional.isPresent()) {
            cartDetailService.delete(cartDetailOptional.get());
        } else {
            throw new DataInputException("Invalid product information");
        }
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
}