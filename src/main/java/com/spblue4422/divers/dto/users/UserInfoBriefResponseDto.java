package com.spblue4422.divers.dto.users;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoBriefResponseDto {
    private Long userId;
    private String loginId;
    private String nickName;
}
