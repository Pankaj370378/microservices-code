package com.userservices.external.services;

import com.userservices.entity.Hotel;
import com.userservices.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name="RATINGSERVICE")
public interface RatingService {
    //get rating
    @GetMapping("/rating/{hotelId}")
    public Rating getRating(@PathVariable("hotelId") String ratingID);

    @GetMapping("/rating/byuserId/{userId}")
    List<Rating> getRatingsByUserId(@PathVariable("userId") String userId);
    // create rating
    @PostMapping("/rating/addRating")
    public Rating addRating(Rating values);

    // update
    @PutMapping("/rating/updateRating/{id}")
    public Rating updateRating(Rating values,@PathVariable("id") String ratingID);

    //delete rating
    @DeleteMapping("/rating/{id}")
    public void deleteRating(@PathVariable("id")String id);

}
