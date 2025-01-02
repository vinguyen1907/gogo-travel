package com.uit.se.gogo.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.uit.se.gogo.entity.Flight;
import com.uit.se.gogo.entity.FlightFavorite;
import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.enums.FlightOrderBy;
import com.uit.se.gogo.enums.SearchOperation;
import com.uit.se.gogo.enums.SeatClass;
import com.uit.se.gogo.exception.CommonException;
import com.uit.se.gogo.mapper.AirlineMapper;
import com.uit.se.gogo.mapper.AirportMapper;
import com.uit.se.gogo.mapper.FlightFavoriteMapper;
import com.uit.se.gogo.mapper.FlightMapper;
import com.uit.se.gogo.repository.FlightFavoriteRepository;
import com.uit.se.gogo.repository.FlightRepository;
import com.uit.se.gogo.repository.UserRepository;
import com.uit.se.gogo.request.FlightCreationRequest;
import com.uit.se.gogo.request.FlightFavoriteRequest;
import com.uit.se.gogo.request.FlightQueryRequest;
import com.uit.se.gogo.response.FlightFavoriteResponse;
import com.uit.se.gogo.response.FlightQueryResponse;
import com.uit.se.gogo.response.FlightResponse;
import com.uit.se.gogo.response.PageDataResponse;
import com.uit.se.gogo.response.UserFlightFavoriteResponse;
import com.uit.se.gogo.service.AirlineService;
import com.uit.se.gogo.service.AirportService;
import com.uit.se.gogo.service.FlightService;
import com.uit.se.gogo.specification.FlightSpecification;
import com.uit.se.gogo.specification.SearchCriteria;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level=AccessLevel.PRIVATE)
@Slf4j
public class FlightServiceImpl implements FlightService{
    FlightRepository flightRepository;
    FlightMapper flightMapper;
    FlightFavoriteRepository flightFavoriteRepository;
    FlightFavoriteMapper flightFavoriteMapper;

    AirportService airportService;
    AirportMapper airportMapper;
    AirlineService airlineService;
    AirlineMapper airlineMapper;

    UserRepository userRepository;

    @Override
    public FlightResponse createFlight(FlightCreationRequest request) {
        log.info(request.toString());
        Flight flight = flightMapper.toFlight(request);

        flight.setArrivalAirport(airportMapper.toAirport(
            airportService.findById(request.getArrivalAirportId())));

        flight.setDepartureAirport(airportMapper.toAirport(
            airportService.findById(request.getDepartureAirportId())));

        flight.setAirline(airlineMapper.toAirline(
            airlineService.findById(request.getAirlineId())));

        flight = flightRepository.save(flight);
        return flightMapper.toFlightResponse(flight);
    }

    @Override
    public FlightResponse getFlight(String id) {
        Flight flight = flightRepository.findById(id).orElseThrow(() -> new CommonException("Flight does not exist"));
        return flightMapper.toFlightResponse(flight);
    }

    @Override
    public List<FlightResponse> getAllFlights() {
        return flightRepository.findAll()
            .stream()
            .map(flightMapper::toFlightResponse)
            .toList();
    }

    @Override
    public FlightFavoriteResponse addFlightFavorite(FlightFavoriteRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new CommonException("user not found"));
        Flight outboundFlight = flightRepository.findById(request.getOutboundFlightId()).orElseThrow();
        
        FlightFavorite flightFavorite = FlightFavorite.builder()
        .user(user)
        .outboundFlight(outboundFlight)
        .build();
        
        if (request.getReturnFlightId() != null) {
            Flight returnFlight = flightRepository.findById(request.getReturnFlightId()).orElseThrow();
            flightFavorite.setReturnFlight(returnFlight);
            if (checkDuplicate(flightFavorite)) {
                throw new CommonException("Duplicate flight favorite");
            }
        }
        
        flightFavorite = flightFavoriteRepository.save(flightFavorite);

        return flightFavoriteMapper.toFlightFavoriteResponse(flightFavorite);
    }

    private boolean checkDuplicate(FlightFavorite flightFavorite) {
        return flightFavoriteRepository.findByUserIdAndOutboundFlightIdAndReturnFlightId(
            flightFavorite.getUser().getId(), 
            flightFavorite.getOutboundFlight().getId(), 
            flightFavorite.getReturnFlight().getId()
        ).isEmpty();
    }

    @Override
    public void removeFlightFavorite(String favoriteFlightId) {
        flightFavoriteRepository.deleteById(favoriteFlightId);
    }

    @Override
    public UserFlightFavoriteResponse getUserFlightFavoriteResponse(String userId) {
        User user = userRepository.findById(userId).orElseThrow();
        List<FlightFavoriteResponse> flightFavoriteResponses = 
            flightFavoriteRepository.findAllByUserId(userId)
                .stream()
                .map(flightFavoriteMapper::toFlightFavoriteResponse)
                .toList();
        
        return new UserFlightFavoriteResponse(user, flightFavoriteResponses);
    }

    @Override
    public PageDataResponse<FlightQueryResponse> searchFlights(FlightQueryRequest request) {
        List<FlightQueryResponse> result;

        // Search for outbound flights
        List<FlightResponse> outboundFlights = searchFlightsByCriteria(
            request.getDepartureLocationId(),
            request.getArrivalLocationId(),
            request.getAirlineId(),
            request.getDepartureTimeFrom(),
            request.getDepartureTimeTo(),
            request.getMinPrice(),
            request.getMaxPrice(),
            request.getSeatClasses(),
            request.getPassengerCount(),
            request.getPage(),
            request.getPageSize(),
            request.getOrderBy()
        );

        if (request.isRoundTrip()) {
            // Search for return flights if it's a round trip
            List<FlightResponse> returnFlights = searchFlightsByCriteria(
                request.getArrivalLocationId(),
                request.getDepartureLocationId(),
                request.getAirlineId(),
                request.getReturnTimeFrom(),
                request.getReturnTimeTo(),
                request.getMinPrice(),
                request.getMaxPrice(),
                request.getSeatClasses(),
                request.getPassengerCount(),
                request.getPage(),
                request.getPageSize(),
                request.getOrderBy()
            );
            // Combine outbound and return flights
            result = combineFlights(outboundFlights, returnFlights);
        } else {
            result = outboundFlights.stream()
                .map(flight -> new FlightQueryResponse(flight, null, false))
                .toList();
        }

        return PageDataResponse.<FlightQueryResponse>builder()
            .data(result)
            .page(request.getPage())
            .size(request.getPageSize())
            .build();
    }

    private List<FlightResponse> searchFlightsByCriteria(
        String departureLocationId,
        String arrivalLocationId,
        String airlineId,
        Instant timeFrom,
        Instant timeTo,
        Double minPrice,
        Double maxPrice,
        List<SeatClass> seatClasses,
        int passengerCount,
        int page,
        int pageSize,
        FlightOrderBy orderBy
    ) {
        FlightSpecification flightSpec = new FlightSpecification();

        // Add location and time criteria
        flightSpec.add(new SearchCriteria("departureLocationId", departureLocationId, SearchOperation.EQUAL));
        flightSpec.add(new SearchCriteria("arrivalLocationId", arrivalLocationId, SearchOperation.EQUAL));
        flightSpec.add(new SearchCriteria("departureTime", timeFrom, SearchOperation.GREATER_THAN_EQUAL));
        flightSpec.add(new SearchCriteria("departureTime", timeTo, SearchOperation.LESS_THAN_EQUAL));
        
        if (airlineId != null) {
            flightSpec.add(new SearchCriteria("airlineId", airlineId, SearchOperation.EQUAL));
        }

        // Add price and seat class criteria if available
        if (minPrice != null && maxPrice != null) {
            flightSpec.add(new SearchCriteria("priceRange", minPrice + "-" + maxPrice, SearchOperation.IN));
        }
        if (seatClasses != null && !seatClasses.isEmpty()) {
            flightSpec.add(new SearchCriteria("seatClass", seatClasses, SearchOperation.IN));
        }

        Pageable pageable = createPageable(page, pageSize, orderBy);

        return flightRepository.findAll(flightSpec, pageable)
            .stream()
            .filter(flight -> flight.getSeats().size() >= passengerCount)
            .map(flightMapper::toFlightResponse)
            .toList();
    }

    private Pageable createPageable(int page, int pageSize, FlightOrderBy orderBy) {
        Sort sort = Sort.unsorted(); 
        switch (orderBy) {
            case QUICKEST -> sort = Sort.by("duration").ascending();
            case BEST -> sort = Sort.by("rating").descending();
            default -> {
            }
        }
        return PageRequest.of(page, pageSize, sort);
    }

    private List<FlightQueryResponse> combineFlights(List<FlightResponse> outboundFlights, List<FlightResponse> returnFlights) {
        List<FlightQueryResponse> result = new ArrayList<>();
        for (var outbound : outboundFlights) {
            for (var returnFlight : returnFlights) {
                result.add(new FlightQueryResponse(outbound, returnFlight, true));
            }
        }
        return result;
    }

}
