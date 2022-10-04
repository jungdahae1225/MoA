package com.moaserver.moa.service;


import com.moaserver.moa.entity.mypage.Member;
import com.moaserver.moa.entity.mypage.MemberRequestDto;
import com.moaserver.moa.exception.MemberException;
import com.moaserver.moa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

//    @Autowired
//    public MemberService(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

    //회원가입
    //패스워드 암호화 추가
    public Member join(MemberRequestDto memberDto) {

        duplicatedMemberByName(memberDto.getEmail());

        String encodedPassword = passwordEncoder.encode(memberDto.getPassword());
        memberDto.setPassword(encodedPassword);

        return memberRepository.save(memberDto.toEntity());
    }

    /*
    이메일(아이디) 중복검사
     */
    private void duplicatedMemberByName(String email) {

        if(memberRepository.findByEmail(email).isPresent()){
            throw new MemberException("이미 사용중인 이메일주소입니다");
        }
    }



    //학교등록
    public Member schoolRegister(Long member_Id, MemberRequestDto.SchoolDto schoolDto) {

        Optional<Member> fMember = memberRepository.findById(member_Id);

        Member member = fMember.get();
        member.SchoolUpdate(schoolDto.getUserSchool(), schoolDto.getLatitude(), schoolDto.getLongitude());

        return memberRepository.save(member);
    }


    //회원정보 수정
    public Member updateProfile(Long member_Id, MemberRequestDto.UpdateDto updateDto) {

        Optional<Member> fMember = memberRepository.findById(member_Id);

        Member member = fMember.get();

        member.ProfileUpdate(updateDto.getNickname(), passwordEncoder.encode(updateDto.getPassword()));

        return memberRepository.save(member);

    }


    //회원탈퇴
    public Long deleteMember(Long member_Id) {

        memberRepository.deleteById(member_Id);

        return member_Id;
    }


    public Optional<Member> findById(Long memberId) {

        return memberRepository.findById(memberId);
    }





}
