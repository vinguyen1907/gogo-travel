package com.uit.se.gogo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.uit.se.gogo.entity.Flight;
import com.uit.se.gogo.entity.Seat;
import com.uit.se.gogo.request.FlightCreationRequest;
import com.uit.se.gogo.response.FlightResponse;
import com.uit.se.gogo.response.SeatResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FlightMapper {
    private final AirlineMapper airlineMapper;

    public Flight toFlight(FlightCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        Flight flight = new Flight();

        if ( request.getArrivalTime() != null ) {
            flight.setArrivalTime( request.getArrivalTime() );
        }
        if ( request.getDepartureTime() != null ) {
            flight.setDepartureTime( request.getDepartureTime() );
        }
        flight.setGate( request.getGate() );
        flight.setTimezone( request.getTimezone() );

        return flight;
    }

    public FlightResponse toFlightResponse(Flight flight) {
        if ( flight == null ) {
            return null;
        }

        return FlightResponse.builder()
            .airline( airlineMapper.toAirlineResponse(flight.getAirline()) )
            .arrivalAirport( flight.getArrivalAirport() )
            .arrivalTime( flight.getArrivalTime() )
            .minBaseFare( flight.getMinBaseFare() )
            .amenities(flight.getAmenities())
            .name(flight.getName())
            .featuredImages( flight.getFeaturedImages() )
            .departureAirport( flight.getDepartureAirport() )
            .departureTime( flight.getDepartureTime() )
            .gate( flight.getGate() )
            .id( flight.getId() )
            .seats( seatListToSeatResponseList( flight.getSeats() ) )
            .timezone( flight.getTimezone() )
            .build();
    }

    protected SeatResponse seatToSeatResponse(Seat seat) {
        if ( seat == null ) {
            return null;
        }

        return SeatResponse.builder()
            .available( seat.isAvailable() )
            .baseFare( seat.getBaseFare() )
            .flight( seat.getFlight() )
            .id( seat.getId() )
            .number( seat.getNumber() )
            .seatClass( seat.getSeatClass() )
            .serviceFee( seat.getServiceFee() )
            .build();
    }

    protected List<SeatResponse> seatListToSeatResponseList(List<Seat> list) {
        if ( list == null ) {
            return List.of();
        }

        List<SeatResponse> list1 = new ArrayList<>( list.size() );
        for ( Seat seat : list ) {
            list1.add( seatToSeatResponse( seat ) );
        }

        return list1;
    }
}
