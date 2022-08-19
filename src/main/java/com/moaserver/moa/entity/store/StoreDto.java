package com.moaserver.moa.entity.store;

import lombok.Data;

@Data
public class StoreDto {
    private String name;
    private double lat; //위도
    private double lng; //경도

    public Store toEntity(){
        return Store.builder()
                .name(name)
                .lat(lat)
                .lng(lng)
                .build();
    }
}
