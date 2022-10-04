package com.moaserver.moa.service;

import com.moaserver.moa.entity.mypage.LoginRequestDto;
import com.moaserver.moa.entity.mypage.Member;
import com.moaserver.moa.entity.mypage.TokenInfo;
import com.moaserver.moa.exception.MemberException;
import com.moaserver.moa.jwt.JwtTokenProvider;
import com.moaserver.moa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class LoginService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    @Transactional
    public TokenInfo login(LoginRequestDto loginRequestDto){
        Member member = memberRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));

        //passwordEncoder.matches(암호화안된비번, 암호화된비번);
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다." + member.getPassword());
        }

        // 로그인에 성공하면 email로 토큰 생성 후 반환
        TokenInfo tokenInfo = jwtTokenProvider.createToken(member.getEmail());

        return tokenInfo;
    }


}
