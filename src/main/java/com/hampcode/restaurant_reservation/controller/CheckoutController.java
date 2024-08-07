package com.hampcode.restaurant_reservation.controller;

import com.hampcode.restaurant_reservation.dto.response.PaypalCaptureResponse;
import com.hampcode.restaurant_reservation.dto.response.PaypalOrderResponse;
import com.hampcode.restaurant_reservation.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    @PostMapping("/paypal/create")
    public PaypalOrderResponse createPaypalOrder(
        @RequestParam Long reservationId,
        @RequestParam String returnUrl,
        @RequestParam String cancelUrl
    ){
        return checkoutService.createPaypalPaymentUrl(reservationId,returnUrl,cancelUrl);
    }

    @PostMapping("/paypal/capture")
    public PaypalCaptureResponse capturePaypalOrder(
            @RequestParam String orderId
    ){
        return checkoutService.capturePaypalPayment(orderId);
    }
}
