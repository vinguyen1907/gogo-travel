package com.uit.se.gogo.mapper;

import org.springframework.stereotype.Component;

import com.uit.se.gogo.entity.Seat;
import com.uit.se.gogo.request.SeatCreationRequest;
import com.uit.se.gogo.response.SeatResponse;

@Component
public class SeatMapper {
    
    public Seat toSeat(SeatCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        Seat.SeatBuilder seat = Seat.builder();

        seat.available( request.isAvailable() );
        seat.baseFare( request.getBaseFare() );
        seat.number( request.getNumber() );
        seat.seatClass( request.getSeatClass() );
        seat.serviceFee( request.getServiceFee() );

        return seat.build();
    }

    public SeatResponse toSeatResponse(Seat seat) {
        if ( seat == null ) {
            return null;
        }

        SeatResponse seatResponse = new SeatResponse();

        seatResponse.setAvailable( seat.isAvailable() );
        seatResponse.setBaseFare( seat.getBaseFare() );
        seatResponse.setFlight( seat.getFlight() );
        seatResponse.setId( seat.getId() );
        seatResponse.setNumber( seat.getNumber() );
        seatResponse.setSeatClass( seat.getSeatClass() );
        seatResponse.setServiceFee( seat.getServiceFee() );

        return seatResponse;
    }
}
