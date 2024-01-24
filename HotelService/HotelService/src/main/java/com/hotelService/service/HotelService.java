package com.hotelService.service;

import com.hotelService.entity.Hotel;

import java.util.List;

public interface HotelService {
    public Hotel createHotel(Hotel hotel);
    public List<Hotel> getAllHotel();
    public Hotel getSingleHotel(String id);
    public void deleteHotel(String id);
    public Hotel updateHotel(Hotel hotel,String id);

}
