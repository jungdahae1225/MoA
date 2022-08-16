package com.moaserver.moa.entity.location;

import lombok.Data;

@Data
public class LocationDto {
    private String name;
    private double lat; //위도
    private double lng; //경도

    public Location toEntity(){
        return Location.builder()
                .name(name)
                .lat(lat)
                .lng(lng)
                .build();
    }
}
