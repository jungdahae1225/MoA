package com.moaserver.moa.entity.mypage;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class LoginRequestDto {

    private String email;
    private String password;

    @Builder
    public LoginRequestDto(String email, String password){
        this.email = email;
        this.password = password;
    }
}
