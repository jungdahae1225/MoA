package com.moaserver.moa.service.store;

import com.moaserver.moa.entity.mypage.Member;
import com.moaserver.moa.entity.store.Store;
import com.moaserver.moa.entity.store.StoreDto;
import com.moaserver.moa.repository.MemberRepository;
import com.moaserver.moa.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//사용자 확인
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    public Store save(StoreDto storeDto) {
        return storeRepository.save(storeDto.toEntity());
    }

    public Member findNearBy(Long memberId) {
        //1.멤버의 등록된 위치를 받음
        Member member = memberRepository.findById(memberId).get();


        //사용자의 위치 정보를 가져온 후 Location DB에서 주변 상점을 모두 찾아 반환 로직 작성

        return member;
    }

}
