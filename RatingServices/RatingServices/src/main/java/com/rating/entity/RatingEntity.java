package com.rating.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.jmx.export.annotation.ManagedResource;

@Entity
@Table(name="RatingTable")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RatingEntity {
    @Id
    private String ratingID;
    private String userID;
    private String hotelID;
    private int rating;
    private String remark;
}
