package com.moaserver.moa.entity.cash;

import com.moaserver.moa.entity.mypage.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Builder
public class Cash {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cash_id")
    private Long id;

    /**
     * 입출금 db를 따로 관리 할까 하다가 일단 합쳐서 작업.
     */
    private String depositDate;
    private String withdrawDate;

    @ManyToOne(fetch = LAZY) //날짜를 저장하기 위함
    @JoinColumn(name = "member_id")
    private Member member;

    private Double cashBalance; //잔액
}
