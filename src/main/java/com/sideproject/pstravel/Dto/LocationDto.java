package com.sideproject.pstravel.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class LocationDto {

    private String userId;
    private String latitude;
    private String longitude;

    @Builder
    public LocationDto(String userId, String latitude, String longitude) {
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
