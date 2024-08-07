package com.hampcode.restaurant_reservation.service;

import com.hampcode.restaurant_reservation.domain.entity.Reservation;
import com.hampcode.restaurant_reservation.domain.entity.Restaurant;
import com.hampcode.restaurant_reservation.domain.entity.User;
import com.hampcode.restaurant_reservation.domain.enums.ReservationStatus;
import com.hampcode.restaurant_reservation.dto.request.ReservationRequestDTO;
import com.hampcode.restaurant_reservation.dto.response.ReservationResponseDTO;
import com.hampcode.restaurant_reservation.exception.ResourceNotFoundException;
import com.hampcode.restaurant_reservation.mapper.ReservationMapper;
import com.hampcode.restaurant_reservation.repository.ReservationRepository;
import com.hampcode.restaurant_reservation.repository.RestaurantRepository;
import com.hampcode.restaurant_reservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final ReservationMapper reservationMapper;

    @Transactional
    public ReservationResponseDTO createReservation
            (ReservationRequestDTO reservationRequestDTO){
        Authentication authentication = SecurityContextHolder.getContext()
                                     .getAuthentication();

        User user = userRepository.findOneByEmail(authentication.getName())
                .orElseThrow(()-> new ResourceNotFoundException("user not found by username"));

        Restaurant restaurant = restaurantRepository.
                findById(reservationRequestDTO.getRestaurantId())
                .orElseThrow(()-> new ResourceNotFoundException("restaurant not found by id"));

        Reservation reservation = reservationMapper.toEntity(reservationRequestDTO);
        reservation.setRestaurant(restaurant);
        reservation.setClient(user);
        reservation.setStatus(ReservationStatus.PENDING);
        reservation.calculateTotalAmount();

        reservation = reservationRepository.save(reservation);

        return reservationMapper.toResponseDto(reservation);
    }

    @Transactional(readOnly = true)
    public List<ReservationResponseDTO> getReservationsByClientId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findOneByEmail(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Reservation> reservations = reservationRepository.findByClientId(user.getId());
        return reservationMapper.toResponseDtoList(reservations);
    }

    @Transactional(readOnly = true)
    public ReservationResponseDTO getReservationById(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));

        return reservationMapper.toResponseDto(reservation);
    }

    @Transactional
    public Reservation confirmReservationPayment(Long reservationId) {
        Reservation reservation = reservationRepository
                .findById(reservationId)
                .orElseThrow(ResourceNotFoundException::new);

        reservation.setStatus(ReservationStatus.PAID);
        return reservationRepository.save(reservation);
    }


}
