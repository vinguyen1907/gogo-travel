package com.uit.se.gogo.service;

import com.uit.se.gogo.dto.StayDTO;
import com.uit.se.gogo.entity.Room;
import com.uit.se.gogo.entity.Stay;
import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.request.AdminCreateStayRequest;
import com.uit.se.gogo.request.SearchStayRequest;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface StayService {
    Stay findById(String id);

    Page<StayDTO> search(SearchStayRequest request);

    List<Room> getAvailableRooms(String stayId, LocalDate checkinDate, LocalDate checkoutDate, Integer guests);

    Stay create(AdminCreateStayRequest request, User user);
}
