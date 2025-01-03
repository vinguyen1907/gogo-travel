package com.uit.se.gogo.response;

import com.uit.se.gogo.dto.StayDTO;
import com.uit.se.gogo.entity.Room;
import com.uit.se.gogo.entity.Stay;
import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.enums.RoomBookingStatus;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetRoomBookingResponse {
    private String id;
    private User user;
    private BigDecimal totalDiscount;
    private BigDecimal totalBill;
    private LocalDate bookingDate;
    private RoomBookingStatus status;
    private Room room;
    private StayDTO stay;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;

    // Guest info
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String country;
}
