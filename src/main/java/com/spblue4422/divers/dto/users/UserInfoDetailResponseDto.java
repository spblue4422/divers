package com.spblue4422.divers.dto.users;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDetailResponseDto extends UserInfoBriefResponseDto{
    private String firstName;
    private String lastName;
}
