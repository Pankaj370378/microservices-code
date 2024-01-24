package com.userservices.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "micro_user")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @Column(name = "USERID")
    private String userId;
    @Column(name="NAME",length = 20)
    private String name;
    @Column(name = "EMAIL",length = 30)
    private String email;
    @Column(name = "ABOUT",length = 20)
    private String about;

    @Transient
    private List<Rating> ratings=new ArrayList<>();
  /*  @Transient
    private Rating rating;*/
}
