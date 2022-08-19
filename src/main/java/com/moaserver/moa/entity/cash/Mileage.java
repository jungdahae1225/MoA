package com.moaserver.moa.entity.cash;

import com.moaserver.moa.entity.mypage.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Mileage {
    /**
     * 사용자의 마일리지
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mileage_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private Double mileageBalance; //잔액
}
