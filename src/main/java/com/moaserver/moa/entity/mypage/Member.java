package com.moaserver.moa.entity.mypage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moaserver.moa.entity.cash.Cash;
import com.moaserver.moa.entity.cash.Mileage;
import com.moaserver.moa.entity.goal.Goal;
import com.moaserver.moa.entity.scrap.Scrap;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Member implements UserDetails {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;       //아이디

    @Column(nullable = false)
    private String password;

    private String userSchool;

    private double latitude;

    private double longitude;

    private String userResidence;

    @OneToOne
    @JoinColumn(name = "cash_id")
    private Cash cash;

    @OneToOne
    @JoinColumn(name = "mileage_id")
    private Mileage mileage;



    @OneToMany(mappedBy = "member")
    private List<Goal> goals = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Scrap> scrap = new ArrayList<>();


    @Builder
    public Member(String nickname, String email, String password, String userSchool){

        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.userSchool = userSchool;

    }




    public void SchoolUpdate(String userSchool, double latitude, double longitude){

        this.userSchool = userSchool;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void ProfileUpdate(String nickname, String password){
        this.nickname = nickname;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
