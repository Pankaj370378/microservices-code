package com.userservices.controller;

import com.userservices.entity.UserEntity;
import com.userservices.services.UserServices;
import com.userservices.services.serviceImpl.UserServicesImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServices userServices;
    private Logger logger= LoggerFactory.getLogger(UserController.class);

    //create user
    @PostMapping("/addUser")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity){
      UserEntity user1 = userServices.saveUser(userEntity);
      return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
    //single user get
    @GetMapping("/{userID}")
    @CircuitBreaker(name = "RatingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<UserEntity> getSingleUser(@PathVariable("userID") String userId){
          UserEntity userEntity=userServices.getUser(userId);
          return ResponseEntity.ok(userEntity);
    }
    //creating fallback method cricuitbreacker
    public ResponseEntity<UserEntity>ratingHotelFallback(String userId,Exception ex){
        logger.info("Fallback is executed beacuse service is down",ex.getMessage());
        UserEntity user = UserEntity.builder()
                .email("pankaj@gmail.com")
                .name("pankaj yadv")
                .about("somehting wrong")
                .userId("123")
                .build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    //all user get
    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserEntity>> getAllUser(){
        List<UserEntity> allUser=userServices.getAllUser();
        return ResponseEntity.ok(allUser);
    }
    //delete user
    @DeleteMapping("/{userID}")
    public ResponseEntity<String>deteleUser(@PathVariable("userID") String userID){
        try {
            userServices.deleteUser(userID);
            return ResponseEntity.ok("User Deleted Sucesscfully");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }
    //update user
    @PutMapping("/updateUser/{userID}")
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity userEntity,@PathVariable("userID") String userID){
          try{
              UserEntity userEntity1=userServices.updateUser(userEntity,userID);
              return ResponseEntity.status(HttpStatus.CREATED).body(userEntity1);
          }catch (Exception e){
              e.printStackTrace();
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

          }

    }
}
