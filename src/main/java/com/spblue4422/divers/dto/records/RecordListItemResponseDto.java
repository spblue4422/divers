package com.spblue4422.divers.dto.records;

import com.spblue4422.divers.spots.Spot;
import com.spblue4422.divers.users.User;
import lombok.*;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordListItemResponseDto {
    private Long recordId;
    private Long userId;
    private Long spotId;
    private String loginId;
    private String nickName;
    private String spotName;
    private String location;
    private Integer logNo;
    private Date diveAt;
    private Date createdAt;
}
