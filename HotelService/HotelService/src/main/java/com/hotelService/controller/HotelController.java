package com.hotelService.controller;

import com.hotelService.entity.Hotel;
import com.hotelService.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;
    //save hotel
    @PostMapping("/saveHotel")
    public ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotel){
        Hotel hotel1=hotelService.createHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

    //get all hotel
    @GetMapping("/getHotel")
    public ResponseEntity<Optional<List<Hotel>>>getlAllHotel(){
        Optional<List<Hotel>> optionalHotel= Optional.ofNullable(hotelService.getAllHotel());
        if (optionalHotel.isEmpty()){
            return ResponseEntity.ok().body(optionalHotel);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    //get single hotel9
    @GetMapping("/{hotelId}")
       public ResponseEntity<Hotel>getSinglehotel(@PathVariable("hotelId")String id){
        Hotel hotel= hotelService.getSingleHotel(id);
        Optional optional=Optional.ofNullable(hotel);
        if (optional.isPresent()){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(hotel);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


       }
    //deletel hotel
    @DeleteMapping("/{id}")
         public ResponseEntity<String> deleteHotel(@PathVariable("id")String id){
        try {
            hotelService.deleteHotel(id);
            return ResponseEntity.ok().body("Sucessfully deleted");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("not deleted");
        }

         }
    //update hotel
    @PutMapping("/{id}")
    public ResponseEntity<Hotel>updateHotel(@RequestBody Hotel hotel,@PathVariable("id")String id){
        Hotel hotel1=hotelService.updateHotel(hotel,id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(hotel);
    }
}
