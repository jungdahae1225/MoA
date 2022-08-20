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
        List<Store> nearStores = new ArrayList<>();

        for(Store store : stores) {
            double storeLat = store.getLat();
            double storeLng = store.getLng();

            double lat = (memberLat - storeLat) * (memberLat - storeLat);
            double lng = (memberLng - storeLng) * (memberLng - storeLng);

            double distance = Math.sqrt(lat + lng);

            if (distance < 50000) {//km로 계산 되기 때문.
                nearStores.add(store);
            }
        }
        return nearStores;
    }
}
