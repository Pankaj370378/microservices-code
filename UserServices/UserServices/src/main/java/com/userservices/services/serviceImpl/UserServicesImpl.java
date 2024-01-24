package com.userservices.services.serviceImpl;

import com.netflix.discovery.converters.Auto;
import com.userservices.entity.Hotel;
import com.userservices.entity.Rating;
import com.userservices.entity.UserEntity;

import com.userservices.exception.ResourceNotFoundException;
import com.userservices.external.services.HotelServices;
import com.userservices.external.services.RatingService;
import com.userservices.repository.UserRepository;
import com.userservices.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelServices hotelServices;
    @Autowired
    private RatingService ratingService;
    private Logger logger= LoggerFactory.getLogger(UserServicesImpl.class);
    @Override
    public UserEntity saveUser(UserEntity user) {
        String randomUserId= UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return this.userRepository.save(user);
//        this code is for saving rating with user
      /* UserEntity userEntity= this.userRepository.save(user);
       Rating rating=new Rating();
       rating.setUserID(userEntity.getUserId());
       Rating rating1=ratingService.addRating(rating);
       userEntity.setRating(rating1);
        return userEntity;*/

    }

    @Override
    public List<UserEntity> getAllUser() {
      List<UserEntity> userList = this.userRepository.findAll();

       List<UserEntity> usersWithRatings = userList.stream().map(user -> {
            List<Rating> ratingList = ratingService.getRatingsByUserId(user.getUserId());

            if (ratingList != null) {
                user.setRatings(ratingList);

                List<Rating> ratingsWithHotels = ratingList.stream().map(rating -> {
                    Hotel hotel = hotelServices.getHotel(rating.getHotelID());
                    logger.info("Hotel status of {}: {}", rating.getHotelID(), hotel != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);

                    rating.setHotel(hotel);
                    return rating;
                }).collect(Collectors.toList());

                user.setRatings(ratingsWithHotels);
            }
            return user;
        }).collect(Collectors.toList());

        userList.addAll(usersWithRatings);






        // Fetch ratings for each user
      /* List<UserEntity> usersWithRatings = userList.stream().map(user -> {
            List<Rating> ratingList = restTemplate.exchange(
                    "http://localhost:8083/rating/byuserId/{userId}",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Rating>>() {},
                    user.getUserId()
            ).getBody();

            if (ratingList != null) {
                // Set ratings to the user
                user.setRatings(ratingList);

                // Fetch hotels for each rating
                List<Rating> ratingsWithHotels = ratingList.stream().map(rating -> {
                    ResponseEntity<Hotel> hotelResponse = restTemplate.getForEntity(
                            "http://localhost:8082/hotel/{hotelId}",
                            Hotel.class,
                            rating.getHotelID()
                    );
                    Hotel hotel = hotelResponse.getBody();
                    logger.info("Hotel status of the {}: {}", rating.getHotelID(), hotelResponse.getStatusCode());

                    // Set the hotel to the rating
                    rating.setHotel(hotel);
                    return rating;
                }).collect(Collectors.toList());

                // Set ratings with hotels to the user
                user.setRatings(ratingsWithHotels);
            }
            return user;
        }).collect(Collectors.toList());

        userList.addAll(usersWithRatings);*/

        return userList;
    }

   /* public List<UserEntity> getAllUser() {
       List<UserEntity> userList=this.userRepository.findAll();

       List<UserEntity> user1=userList.stream().map(user->{
           List <Rating>forEntity=restTemplate.getForObject("http://localhost:8083/rating/byuserId/"+user.getUserId(),ArrayList.class);
            List <Rating>ratingList=forEntity.stream().toList();
            user.setRatings(ratingList);
            return user;
       }).collect(Collectors.toList());
        List<Rating>ratingList=ratings.stream().map(rating -> {
            //api call to hotel service to get hotel
            //http://localhost:8082/hotel/0a4fd3f2-19f2-4684-87e2-b99abc5fe43c
            ResponseEntity<Hotel>forEntity= restTemplate.getForEntity("http://localhost:8082/hotel/"+rating.getHotelID(), Hotel.class);
            Hotel hotel=forEntity.getBody();
            logger.info("Hotel status of the {}",forEntity.getStatusCode());
            //set the hotel to rating
            rating.setHotel(hotel);
            return rating;

        }).collect(Collectors.toList());

        userList.addAll(user1);

       return userList;
    }*/

    @Override
    public UserEntity getUser(String userid) {
       UserEntity userEntity= this.userRepository.findById(userid).orElseThrow(()->new ResourceNotFoundException("user not found given id : "+ userid));

       //fetch rating of the above user from Rating Service;
        //http://localhost:8083/rating/byuserId/ee183e80-8f6e-4b34-b819-4115debd1ee7
        //i am going to use Rest Templet for calling for other service
//       Rating []ratingObject= restTemplate.getForObject("http://RATINGSERVICE/rating/byuserId/b16cc462-1e5e-4c89-ade9-a3ce86ee44db", Rating[].class); //getting all rating accourding to userid
        List<Rating> ratingsByUserId = ratingService.getRatingsByUserId(userEntity.getUserId());
        logger.info("this is the rating data{} ",ratingsByUserId);
//       List<Rating> ratings= Arrays.stream(ratingList).toList();
       //fetching data from hotel service
           List<Rating>ratingList=ratingsByUserId.stream().map(rating -> {
                //api call to hotel service to get hotel
               //http://localhost:8082/hotel/0a4fd3f2-19f2-4684-87e2-b99abc5fe43c
            // ResponseEntity<Hotel>forEntity= restTemplate.getForEntity("http://HOTELSERVICE/hotel/"+rating.getHotelID(), Hotel.class);
//             Hotel hotel=forEntity.getBody();
               Hotel hotel=hotelServices.getHotel(rating.getHotelID());
//             logger.info("Hotel status of the {}",forEntity.getStatusCode());
               //set the hotel to rating
               rating.setHotel(hotel);
             return rating;

                }).collect(Collectors.toList());

       userEntity.setRatings(ratingList);
        return userEntity;
    }

    @Override
    public void deleteUser(String UserId) {
              userRepository.deleteById(UserId);
    }

    @Override
    public UserEntity updateUser(UserEntity user,String userID) {
        user.setUserId(userID);
        UserEntity userEntity1= userRepository.save(user);
        return userEntity1;
    }
}
