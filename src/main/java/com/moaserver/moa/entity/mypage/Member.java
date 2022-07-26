package com.moaserver.moa.entity.mypage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickname;    //아이디

    private String email;

    @Column(nullable = false)
    private String password;

    private String userSchool;
    private String userResidence;


    @Builder
    public Member(String nickname, String email, String password, String userSchool){

        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.userSchool = userSchool;

    }

    public void SchoolUpdate(String userSchool){
        this.userSchool = userSchool;
    }

    public void ProfileUpdate(String email, String password){
        this.email = email;
        this.password = password;
    }

}
