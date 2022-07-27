package com.moaserver.moa.entity.mileage;

import com.moaserver.moa.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Mileage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mileage_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private Double mileageBalance; //잔액
}
