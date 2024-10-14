package com.uit.se.gogo.service;

import com.uit.se.gogo.entity.Room;
import com.uit.se.gogo.entity.Stay;
import com.uit.se.gogo.request.SearchStayRequest;

import java.util.Date;
import java.util.List;

public interface StayService {
    Stay findById(String id);

    List<Stay> search(SearchStayRequest request);

    List<Room> getAvailableRooms(String stayId, Date checkinDate, Date checkoutDate, Integer guests);
}
