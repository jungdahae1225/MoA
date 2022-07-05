package com.moaserver.moa.entity.location;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Builder @Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    private String name; //상점이름
    private double lat; //위도
    private double lng; //경도
}
