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
    private Long id;
    private User user;
    private Spot spot;
    private int logNo;
    private Date diveAt;
    private Date createdAt;
}
