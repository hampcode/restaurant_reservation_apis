package com.hampcode.restaurant_reservation.dto.response;

import lombok.Data;

@Data
public class PaypalCaptureResponse {
    private boolean completed;
    private Long reservationId;
}
