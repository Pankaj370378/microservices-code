package com.userservices.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private String ratingID;
    private String userID;
    private String hotelID;
    private int rating;
    private String remark;
    private Hotel hotel;

}
