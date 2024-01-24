package com.hotelService.HotelRepository;

import com.hotelService.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepo extends JpaRepository<Hotel ,String> {

}
