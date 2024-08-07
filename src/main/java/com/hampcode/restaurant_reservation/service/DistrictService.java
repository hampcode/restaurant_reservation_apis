package com.hampcode.restaurant_reservation.service;

import com.hampcode.restaurant_reservation.domain.entity.District;
import com.hampcode.restaurant_reservation.dto.response.DistrictResponseDTO;
import com.hampcode.restaurant_reservation.mapper.DistrictMapper;
import com.hampcode.restaurant_reservation.repository.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DistrictService {
    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;

    public List<DistrictResponseDTO> getAllDistricts() {
        List<District> districts = districtRepository.findAll();
        return districtMapper.toResponseDtoList(districts);
    }

}
