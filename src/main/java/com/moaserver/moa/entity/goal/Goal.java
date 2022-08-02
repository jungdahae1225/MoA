package com.moaserver.moa.entity.goal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moaserver.moa.entity.mypage.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.YearMonth;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Goal {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "goal_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long price;

    @ColumnDefault("false")
    private boolean completed;  //false: 미달성, true: 달성

    @Column(nullable = false)
    private YearMonth date;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @Builder
    public Goal(Long id, String content, Long price, boolean completed, YearMonth date, Member member) {

        this.id = id;
        this.content = content;
        this.price = price;
        this.completed = completed;
        this.date = date;
        this.member = member;
    }
}


