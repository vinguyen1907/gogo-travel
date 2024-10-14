package com.uit.se.gogo.request;

import com.uit.se.gogo.enums.StayOrderBy;
import com.uit.se.gogo.enums.StayType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchStayRequest {
    private String locationId;
    private Date checkinDate;
    private Date checkoutDate;
    private Integer rooms;
    private Integer guests;
    private Double minPrice;
    private Double maxPrice;
    private StayType type;
    private Integer rating;
    private StayOrderBy orderBy;
    private Integer page;
    private Integer pageSize;
}
