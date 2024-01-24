package com.userservices.external.services;

import com.userservices.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="HOTELSERVICE")
public interface HotelServices {
    @GetMapping("/hotel/{hotelId}")
    public Hotel getHotel(@PathVariable("hotelId") String hotelId);
 /*   @GetMapping("/hotel/{hotelId}")
  Hotel getHotelById(@PathVariable("hotelId") String hotelId);*/


}

