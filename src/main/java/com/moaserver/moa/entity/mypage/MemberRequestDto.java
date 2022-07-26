package com.moaserver.moa.entity.mypage;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class MemberRequestDto {

    @NotBlank(message = "닉네임 입력은 필수입니다")
    private String nickname;

    @Email(message = "유효한 형식의 이메일이 아닙니다")
    private String email;

    @NotBlank(message = "패스워드 입력은 필수입니다")
    private String password;
    private String userSchool;



    //dto -> entity
    public Member toEntity(){

        Member member = Member.builder()
                .nickname(nickname)
                .email(email)
                .password(password)
                .userSchool(userSchool)
                .build();

        return member;
    }

    @Getter
    @NoArgsConstructor
    public static class SchoolDto {

        private String userSchool;

        @Builder
        public SchoolDto(String userSchool){
            this.userSchool = userSchool;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class UpdateDto {

        private String email;
        private String password;

        @Builder
        public UpdateDto(String email, String password){
            this.email = email;
            this.password = password;
        }
    }

}
