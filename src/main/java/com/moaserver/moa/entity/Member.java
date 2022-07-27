package com.moaserver.moa.entity;

import com.moaserver.moa.entity.cash.Cash;
import com.moaserver.moa.entity.mileage.Mileage;
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
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

   @OneToOne
   @JoinColumn(name = "cash_id")
    private Cash cash;

    @OneToOne
    @JoinColumn(name = "mileage_id")
   private Mileage mileage;
}
