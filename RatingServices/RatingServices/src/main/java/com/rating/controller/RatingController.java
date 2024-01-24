package com.rating.controller;
import com.rating.entity.RatingEntity;
import com.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;
    // save rating
    @PostMapping("/addRating")
    public ResponseEntity<RatingEntity> saveRating(@RequestBody RatingEntity ratingEntity){
        RatingEntity ratingEntity1=ratingService.addRating(ratingEntity);
        Optional optional=Optional.ofNullable(ratingEntity);
       // RatingEntity optional=Optional.ofNullable(ratingEntity).orElseThrow(()->new ResourceNotFoundException());
        if(!optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body(ratingEntity1);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    //get All rating
    @GetMapping("/getAll")
    public ResponseEntity<List<RatingEntity>> getAllRating(){
        List<RatingEntity>allRating=this.ratingService.getAllRating();
        Optional optional=Optional.ofNullable(allRating);
        if (!optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body(allRating);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    //get rating By user id
    @GetMapping("/byuserId/{userId}")
    public ResponseEntity<List<RatingEntity>> getRatingByUserID(@PathVariable("userId")String userId){
        List<RatingEntity>allRating=this.ratingService.getRatingByUserId(userId);
        Optional optional=Optional.ofNullable(allRating);
        if (!optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body(allRating);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    //get rating By hotel id
    @GetMapping("/byhotelId/{hotelId}")
    public ResponseEntity<List<RatingEntity>> getRatingByHotelID(@PathVariable("hotelId")String userId){
        List<RatingEntity>allRating=this.ratingService.getRatingByHotelId(userId);
        Optional optional=Optional.ofNullable(allRating);
        if (!optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body(allRating);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    //get single rating
    @GetMapping("/{id}")
    public ResponseEntity<RatingEntity> getSingleRating(@PathVariable("id")String id){
        RatingEntity allSingleRating=this.ratingService.getSingleRating(id);
        Optional optional=Optional.ofNullable(allSingleRating);
        if (!optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body(allSingleRating);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    //delete rating
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRating(@PathVariable("id")String id){
      try{
          this.ratingService.deleteRating(id);
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("record Deleted Successfully");
      }catch (Exception e){
          e.printStackTrace();
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("record not Deleted Successfully");
      }
    }
    //update rating
    @PutMapping("/updateRating/{id}")
    public ResponseEntity<RatingEntity> updateRating(@RequestBody RatingEntity ratingEntity ,@PathVariable("id")String id){
        RatingEntity ratingEntity1=ratingService.updateRating(ratingEntity,id);
        Optional optional=Optional.ofNullable(ratingEntity);
        // RatingEntity optional=Optional.ofNullable(ratingEntity).orElseThrow(()->new ResourceNotFoundException());
        if(!optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body(ratingEntity1);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
