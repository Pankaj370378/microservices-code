package com.rating.service;

import com.rating.entity.RatingEntity;

import java.util.List;

public interface RatingService {
    public RatingEntity addRating(RatingEntity ratingEntity);
    public List<RatingEntity> getAllRating();
    public RatingEntity getSingleRating(String id);
    public void deleteRating(String id);
    public RatingEntity updateRating(RatingEntity ratingEntity,String id);
    public List<RatingEntity> getRatingByUserId(String userId);
    public List<RatingEntity> getRatingByHotelId(String userId);


}
