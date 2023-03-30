package com.spblue4422.divers.dto.records;

import com.spblue4422.divers.spots.Spot;

import java.util.Date;

public interface RecordListItemInfo {
    Long getRecordId();
    Integer getLogNo();
    Date getDiveAt();
    Boolean getOpened();
    Date getCreatedAt();
    UserInfo getUser();
    Spot getSpot();

    interface UserInfo {
        Long getUserId();
        String getLoginId();
        String getNickName();
    }

    interface SpotInfo {
        Long getSpotId();
        String getSpotName();
        String getLocation();
    }
}