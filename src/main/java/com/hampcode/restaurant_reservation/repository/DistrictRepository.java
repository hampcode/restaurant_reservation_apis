package com.hampcode.restaurant_reservation.repository;

import com.hampcode.restaurant_reservation.domain.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DistrictRepository extends JpaRepository<District, Long> {
    Optional<District> findByName(String name);
}
