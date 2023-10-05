package com.cg.cart;

import com.cg.cart.cartDetail.ICartDetailService;
import com.cg.cart.cartDetail.dto.CartItemParam;
import com.cg.cart.dto.CartResult;
import com.cg.model.CartItem;
import com.cg.order.dto.OrderCreationParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carts")
public class CartAPI {
    private final ICartService cartService;
    private final ICartDetailService cartDetailService;

    @GetMapping("/{cartId}")
    @ResponseStatus(HttpStatus.OK)
    public CartResult findAllCart(@PathVariable Long cartId) {
        return cartService.getById(cartId);
    }

    @GetMapping("/cartItem/{cartId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CartItem> findAllCartItem(@PathVariable Long cartId) {
        return cartDetailService.findAllByCartId(cartId);
    }

    @PostMapping("/addToCart/{cartId}")
    @ResponseStatus(HttpStatus.OK)
    public CartResult addToCart(@RequestBody CartItemParam cartItemParam, @PathVariable Long cartId) {
//        @AuthenticationPrincipal UserPrincipal principal
        cartService.addCartItem(cartId, cartItemParam, cartItemParam.getUserId());
        return cartService.getById(cartId);
    }

    @PatchMapping("/change-quantity/{cartItemId}")
    @ResponseStatus(HttpStatus.OK)
    public CartItem changeQuantity(@RequestBody CartItemParam cartItemParam, @PathVariable Long cartItemId) {
        cartDetailService.changeQuantity(cartItemId, cartItemParam);
        return cartDetailService.findById(cartItemId);
    }

    @PostMapping("/payment/{cartId}")
    @ResponseStatus(HttpStatus.OK)
    public CartResult payment(@RequestBody CartItemParam cartItemParam, OrderCreationParam orderCreationParam, @PathVariable Long cartId) {
        cartService.payment(cartId, cartItemParam, orderCreationParam, cartItemParam.getUserId());
        return cartService.getById(cartId);
    }


//    @PatchMapping("/order/{id}")
//    public ResponseEntity<?> updateBillStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
//        Order billOptional = billService.findById(id);
//
////        if (billOptional.isEmpty()) {
////            throw new DataInputException("Bill not found with id: " + id);
////        }
//
////        bill.setStatus(EPayment.valueOf(request.get("status")));
////        billService.save(bill);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


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

}