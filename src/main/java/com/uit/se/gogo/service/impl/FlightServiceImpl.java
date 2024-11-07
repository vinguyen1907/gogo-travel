package com.uit.se.gogo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightServiceImpl implements FlightService{
    private final FlightRepository flightRepository;
    private FlightMapper flightMapper = new FlightMapper();
    private final FlightFavoriteRepository flightFavoriteRepository;
    private FlightFavoriteMapper flightFavoriteMapper = new FlightFavoriteMapper();

    private final AirportService airportService;
    private AirportMapper airportMapper = new AirportMapper();
    private final AirlineService airlineService;
    private AirlineMapper airlineMapper = new AirlineMapper();

    private final UserRepository userRepository;

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
        Flight flight = flightRepository.findById(id).orElseThrow(() -> new RuntimeException("Flight does not exist"));
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
        User user = new User();
        user.setFirstName(request.getUserId());
        user.setLastName(request.getUserId());
        Flight outboundFlight = flightRepository.findById(request.getOutboundFlightId()).orElseThrow();
        
        FlightFavorite flightFavorite = FlightFavorite.builder()
        .user(user)
        .outboundFlight(outboundFlight)
        .build();
        
        if (request.getReturnFlightId() != null) {
            Flight returnFlight = flightRepository.findById(request.getReturnFlightId()).orElseThrow();
            flightFavorite.setReturnFlight(returnFlight);
        }
        
        flightFavorite = flightFavoriteRepository.save(flightFavorite);

        return flightFavoriteMapper.toFlightFavoriteResponse(flightFavorite);
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
            request.getDepartureTimeFrom(),
            request.getDepartureTimeTo(),
            request.getMinPrice(),
            request.getMaxPrice(),
            request.getSeatClasses(),
            request.getPage(),
            request.getPageSize(),
            request.getOrderBy()
        );

        if (request.isRoundTrip()) {
            // Search for return flights if it's a round trip
            List<FlightResponse> returnFlights = searchFlightsByCriteria(
                request.getArrivalLocationId(),
                request.getDepartureLocationId(),
                request.getReturnTimeFrom(),
                request.getReturnTimeTo(),
                request.getMinPrice(),
                request.getMaxPrice(),
                request.getSeatClasses(),
                request.getPage(),
                request.getPageSize(),
                request.getOrderBy()
            );
            // Combine outbound and return flights
            result = combineFlights(outboundFlights, returnFlights);
        } else {
            result = outboundFlights.stream()
                .map(flight -> new FlightQueryResponse(flight, null, false))
                .collect(Collectors.toList());
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
        Date timeFrom,
        Date timeTo,
        Double minPrice,
        Double maxPrice,
        List<SeatClass> seatClasses,
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
            .map(flightMapper::toFlightResponse)
            .collect(Collectors.toList());
    }

    private Pageable createPageable(int page, int pageSize, FlightOrderBy orderBy) {
        Sort sort = Sort.unsorted(); 
        switch (orderBy) {
            case QUICKEST:
                sort = Sort.by("duration").ascending();
                break;
            case BEST:
                sort = Sort.by("rating").descending();
                break;
            default:
                break;
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
