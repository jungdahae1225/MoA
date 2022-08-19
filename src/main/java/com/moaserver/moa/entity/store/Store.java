package com.moaserver.moa.entity.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Builder @Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    private String name; //상점이름

    private double lat; //위도
    private double lng; //경도
}
