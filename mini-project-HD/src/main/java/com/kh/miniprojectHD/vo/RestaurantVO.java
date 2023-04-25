package com.kh.miniprojectHD.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestaurantVO {
    private String memId;
    private String restId;
    private String restName;
    private String restDate;
    private boolean isAvailable;
    private String category;
}
