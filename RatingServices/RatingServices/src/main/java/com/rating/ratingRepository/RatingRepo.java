package com.rating.ratingRepository;

import com.rating.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepo extends JpaRepository<RatingEntity,String> {
//    custom finder method
    List<RatingEntity> findByUserID(String userId);
    List<RatingEntity> findByHotelID(String userId);
}
