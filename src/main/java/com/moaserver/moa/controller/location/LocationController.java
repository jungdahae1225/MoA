package com.moaserver.moa.controller.location;

import com.moaserver.moa.entity.Member;
import com.moaserver.moa.entity.location.Location;
import com.moaserver.moa.entity.location.LocationDto;
import com.moaserver.moa.repository.MemberRepository;
import com.moaserver.moa.service.Location.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;

    @PostMapping("/location")
    public ResponseEntity<Location> signUp(@RequestBody LocationDto locationDto) {
        Location location = locationService.save(locationDto);
        return new ResponseEntity(locationDto, HttpStatus.CREATED);
    }

    @GetMapping("/location/{memberid}")
    public ResponseEntity<Location> signUp(@PathVariable Long memberId) {
        Location location = locationService.findNearBy(memberId);


        return new ResponseEntity(locationDto, HttpStatus.CREATED);
    }

}
