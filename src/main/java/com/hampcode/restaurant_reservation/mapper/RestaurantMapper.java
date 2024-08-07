package com.hampcode.restaurant_reservation.mapper;

import com.hampcode.restaurant_reservation.domain.entity.Restaurant;
import com.hampcode.restaurant_reservation.dto.response.RestaurantResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class RestaurantMapper {

    private final ModelMapper modelMapper;

    public RestaurantResponseDTO toResponseDto(Restaurant restaurant) {
        return modelMapper.map(restaurant, RestaurantResponseDTO.class);
    }


}
