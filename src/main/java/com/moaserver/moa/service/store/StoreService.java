package com.moaserver.moa.service.store;

import com.moaserver.moa.entity.mypage.Member;
import com.moaserver.moa.entity.store.Store;
import com.moaserver.moa.entity.store.StoreDto;
import com.moaserver.moa.repository.MemberRepository;
import com.moaserver.moa.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    public Store save(StoreDto storeDto) {
        return storeRepository.save(storeDto.toEntity());
    }

    public List<Store> findNearBy(Long memberId) {
        //1.멤버의 등록된 위치를 받음
        //거리 계산 (멤버 경도 - 상점 경도)제곱 + (멤버 위도 - 상점 위도)제곱의 제곱근
        Member member = memberRepository.findById(memberId).get();
        double memberLat = member.getLatitude(); //멤버의 위도
        double memberLng = member.getLongitude(); //멤버의 경도

        List<Store> stores = storeRepository.findAll();
        List<Store> myStores = new ArrayList<>();

        for (Store store : stores) {
            if (distance(memberLat, memberLng, store.getLat(), store.getLng(), "meter") < 500)
                myStores.add(store);
        }
        return myStores;
    }

    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        if (unit == "kilometer") {
            dist = dist * 1.609344;
        } else if(unit == "meter"){
            dist = dist * 1609.344;
        }

        return (dist);
    }


    // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

}

