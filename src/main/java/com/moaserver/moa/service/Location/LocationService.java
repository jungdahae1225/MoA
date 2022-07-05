package com.moaserver.moa.service.Location;

import com.moaserver.moa.entity.Member;
import com.moaserver.moa.entity.location.Location;
import com.moaserver.moa.entity.location.LocationDto;
import com.moaserver.moa.repository.LocationRepository;
import com.moaserver.moa.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;
    private final MemberRepository memberRepository;

    public Location save(LocationDto locationDto) {
        return locationRepository.save(locationDto.toEntity());
    }

    public Location findNearBy(Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        Member member = findMember.get();
        
        //사용자의 위치 정보를 가져온 후 Location DB에서 주변 상점을 모두 찾아 반환 로직 작성

        return ;
    }

}
