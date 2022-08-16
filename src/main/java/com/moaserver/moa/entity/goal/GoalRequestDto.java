package com.moaserver.moa.entity.goal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moaserver.moa.entity.mypage.Member;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.YearMonth;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class GoalRequestDto {

    @NotBlank(message = "목표x")
    private String content;

    @NotNull(message = "가격x")
    private Long price;

    private boolean completed;

    @DateTimeFormat(pattern = "yyyy-MM")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM", timezone = "Asia/Seoul")
    private YearMonth date;

    private Member member;

    //dto -> entity
    public Goal toEntity(){

        Goal goal = Goal.builder()
                .content(content)
                .price(price)
                .completed(completed)
                .date(date)
                .member(member)
                .build();

        return goal;
    }


    @Getter
    @NoArgsConstructor
    public static class UpdateDto {

        private String content;
        private Long price;
        private boolean completed;

        @Builder
        public UpdateDto(String content, Long price, boolean completed){
            this.content = content;
            this.price = price;
            this.completed = completed;
        }
    }



}
