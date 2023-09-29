package com.cg.order;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"/api/orders", "/api/users/"})
public class OrderAPI {

    private final IOrderService orderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<?> findAll(String status) {
        if (status == null)
            return orderService.findAll();
        return orderService.findAllByStatus(status);
    }


    @GetMapping("/{userId}/orders")
    @PreAuthorize("#userId == principal.id")
    @ResponseStatus(HttpStatus.OK)
    public List<?> findAllByUserId(@PathVariable Long userId, String status) {
        if (status != null)
            return orderService.findAllByUserIdAndStatus(userId, status);
        return orderService.findAllByUserId(userId);
    }


    @GetMapping("/findAllByCreatedAt")
    @ResponseStatus(HttpStatus.OK)
    public List<?> findAllByCreatedAt() {
        return orderService.findAllByCreatedAt(LocalDate.now());
    }
}
