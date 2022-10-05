package com.moaserver.moa.entity.mypage;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class TokenInfo {

    private String accessToken;
    private Long id;



}
