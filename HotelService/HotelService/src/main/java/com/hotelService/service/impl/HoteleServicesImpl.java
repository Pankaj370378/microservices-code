package com.hotelService.service.impl;

import com.hotelService.HotelRepository.HotelRepo;
import com.hotelService.entity.Hotel;
import com.hotelService.exception.ResourceNotFoundException;
import com.hotelService.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HoteleServicesImpl implements HotelService {
    @Autowired
    private HotelRepo hotelRepo;
    @Override
    public Hotel createHotel(Hotel hotel) {
        String randomId= UUID.randomUUID().toString();
        hotel.setId(randomId);
        return this.hotelRepo.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotel() {

        return this.hotelRepo.findAll();
    }

    @Override
    public Hotel getSingleHotel(String id) {
        return this.hotelRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel with given id is not found :" +id));
    }

    @Override
    public void deleteHotel(String id) {

        hotelRepo.deleteById(id);
    }

    @Override
    public Hotel updateHotel(Hotel hotel, String id) {
        hotel.setId(id);
        return hotelRepo.save(hotel);
    }
}
