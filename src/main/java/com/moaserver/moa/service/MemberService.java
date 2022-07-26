package com.moaserver.moa.service;

import com.moaserver.moa.entity.mypage.Member;
import com.moaserver.moa.entity.mypage.MemberRequestDto;
import com.moaserver.moa.exception.MemberException;
import com.moaserver.moa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;


    //회원가입
    public Member join(MemberRequestDto memberDto) {

        duplicatedMemberByName(memberDto.getNickname());

        return memberRepository.save(memberDto.toEntity());
    }

    /*
    닉네임 중복검사
     */
    private void duplicatedMemberByName(String name){
        if(findByNickname(name).isPresent()){
            throw new MemberException("이미 존재하는 닉네임입니다");
        }
    }

    //학교등록
    public Member schoolRegister(Long member_Id, MemberRequestDto.SchoolDto schoolDto){

        Optional<Member> fMember = memberRepository.findById(member_Id);

        Member member = fMember.get();
        member.SchoolUpdate(schoolDto.getUserSchool());

        return memberRepository.save(member);
    }

    //회원정보 수정
    public Member updateProfile(Long member_Id, MemberRequestDto.UpdateDto updateDto){

        Optional<Member> fMember = memberRepository.findById(member_Id);

        Member member = fMember.get();

        member.ProfileUpdate(updateDto.getEmail(), updateDto.getPassword());

        return memberRepository.save(member);

    }



    public Optional<Member> findById(Long memberId){

        return memberRepository.findById(memberId);
    }

    public Optional<Member> findByNickname(String name){
        return memberRepository.findByNickname(name);
    }



}
