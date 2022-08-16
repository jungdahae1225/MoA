package com.moaserver.moa.controller;

import com.moaserver.moa.entity.mypage.Member;
import com.moaserver.moa.entity.mypage.MemberRequestDto;
import com.moaserver.moa.exception.MemberException;
import com.moaserver.moa.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    /*
    회원가입 API
     */
    @PostMapping("/signUp")
    public Member join(@RequestBody @Valid MemberRequestDto memberDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new MemberException("입력값을 다시 확인해주세요");
        }

        return memberService.join(memberDto);
    }


    /*
    학교등록 API
     */
    @PutMapping("/myPage/school/{id}")
    public Member schoolResister(@PathVariable Long id, @RequestBody @Valid MemberRequestDto.SchoolDto schoolDto,  BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new MemberException("입력값을 다시 확인해주세요");
        }

        return memberService.schoolRegister(id, schoolDto);
    }




    /*
    회원정보 조회 API
     */
    @GetMapping("/myPage/{id}")
    public ResponseEntity<Member> getMember(@PathVariable Long id) {

        Optional<Member> member = memberService.findById(id);

        if(member.isEmpty())
            throw new MemberException("찾는 아이디가 없습니다.");

        return new ResponseEntity(member, HttpStatus.OK);
    }



    /*
    회원정보 수정 API
     */
    @PutMapping("/myPage/update/{id}")
    public Member updateProfile(@PathVariable Long id, @RequestBody @Valid MemberRequestDto.UpdateDto updateDto,  BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new MemberException("입력값 오류");
        }

        return memberService.updateProfile(id, updateDto);


    }


    /*
    회원탈퇴 API
     */
    @DeleteMapping("/myPage/{id}")
    public Long deleteMember(@PathVariable Long id) {

        Long memberId = memberService.deleteMember(id);

        return memberId;
    }



}
