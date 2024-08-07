package com.hampcode.restaurant_reservation.service;

import com.hampcode.restaurant_reservation.domain.entity.Restaurant;
import com.hampcode.restaurant_reservation.dto.response.RestaurantResponseDTO;
import com.hampcode.restaurant_reservation.exception.ResourceNotFoundException;
import com.hampcode.restaurant_reservation.mapper.RestaurantMapper;
import com.hampcode.restaurant_reservation.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public Page<RestaurantResponseDTO> getAllRestaurants(Pageable pageable) {
        Page<Restaurant> restaurants = restaurantRepository.findAll(pageable);
        return restaurants.map(restaurantMapper::toResponseDto);
    }

    public Page<RestaurantResponseDTO> findByDistrictName(String districtName, Pageable pageable) {
        Page<Restaurant> restaurants = restaurantRepository.findByDistrictName(districtName, pageable);
        return restaurants.map(restaurantMapper::toResponseDto);
    }

    public RestaurantResponseDTO getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + id));
        return restaurantMapper.toResponseDto(restaurant);
    }

}
