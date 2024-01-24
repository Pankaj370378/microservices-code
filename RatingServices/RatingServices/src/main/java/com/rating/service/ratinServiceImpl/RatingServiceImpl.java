package com.rating.service.ratinServiceImpl;

import com.rating.entity.RatingEntity;
import com.rating.exception.ResourceNotFoundException;
import com.rating.ratingRepository.RatingRepo;
import com.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepo ratingRepo;
    @Override
    public RatingEntity addRating(RatingEntity ratingEntity) {
        String randomRatingId= UUID.randomUUID().toString();
        ratingEntity.setRatingID(randomRatingId);
        return this.ratingRepo.save(ratingEntity);
    }

    @Override
    public List<RatingEntity> getAllRating() {
        return ratingRepo.findAll();
    }

    @Override
    public RatingEntity getSingleRating(String id) {
        return this.ratingRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("there is no data given criteria"));
    }

    @Override
    public void deleteRating(String id) {
  this.ratingRepo.deleteById(id);
    }

    @Override
    public RatingEntity updateRating(RatingEntity ratingEntity, String id) {
        ratingEntity.setRatingID(id);
        return this.ratingRepo.save(ratingEntity);
    }

    @Override
    public List<RatingEntity> getRatingByUserId(String userId) {
        return this.ratingRepo.findByUserID(userId);
    }

    @Override
    public List<RatingEntity> getRatingByHotelId(String userId) {
           return this.ratingRepo.findByHotelID(userId);
    }
}
