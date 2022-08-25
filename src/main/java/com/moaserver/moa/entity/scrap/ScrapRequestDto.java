package com.moaserver.moa.entity.scrap;
import com.moaserver.moa.entity.mypage.Member;
import com.moaserver.moa.entity.store.Store;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ScrapRequestDto {

    private Member member;

    private Store store;


    //dto -> entity
    public Scrap toEntity(){

        Scrap scrap = Scrap.builder()
                .member(member)
                .store(store)
                .build();

        return scrap;
    }
}
