package com.uit.se.gogo.specification;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.uit.se.gogo.entity.Flight;
import com.uit.se.gogo.entity.Seat;
import com.uit.se.gogo.enums.SeatClass;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlightSpecification implements Specification<Flight> {

    private List<SearchCriteria> criteriaList;

    public FlightSpecification() {
        this.criteriaList = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        criteriaList.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<Flight> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        // Join with Seat entity
        Join<Flight, Seat> seatJoin = root.join("seats");

        for (SearchCriteria criteria : criteriaList) {
            // Handle price range for Seat (baseFare + serviceFee)
            if (criteria.getKey().equals("priceRange")) {
                String[] prices = criteria.getValue().toString().split("-");
                Double minPrice = Double.valueOf(prices[0]);
                Double maxPrice = Double.valueOf(prices[1]);

                // Total fare is baseFare + serviceFee
                Expression<Double> totalFare = builder.sum(seatJoin.get("baseFare"), seatJoin.get("serviceFee"));

                // Filter flights where at least one seat has totalFare between minPrice and maxPrice
                predicates.add(
                    builder.between(totalFare, minPrice, maxPrice)
                );
            } else if (criteria.getKey().equals("seatClass")) {
                List<SeatClass> seatClasses = (List<SeatClass>) criteria.getValue();

                // Filter flights where at least one seat has a class in the provided list
                predicates.add(
                    seatJoin.get("seatClass").in(seatClasses)
                );
            } else {
                switch (criteria.getOperation()) {
                    case EQUAL -> {
                        switch (criteria.getKey()) {
                            case "departureLocationId" -> predicates.add(builder.equal(
                                    root.get("departureAirport")
                                            .get("location")
                                            .get("id"), criteria.getValue()));
                            case "arrivalLocationId" -> predicates.add(builder.equal(
                                    root.get("arrivalAirport")
                                            .get("location")
                                            .get("id"), criteria.getValue()));
                            default -> predicates.add(builder.equal(root.get(criteria.getKey()), criteria.getValue()));
                        }
                    }
                    
                    case GREATER_THAN -> {
                        if (criteria.getValue() instanceof Date date) {
                            predicates.add(builder.greaterThan(root.<Date>get(criteria.getKey()), date));
                        } else {
                            predicates.add(builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString()));
                        }
                    }

                    
                    case LESS_THAN -> {
                        if (criteria.getValue() instanceof Date date) {
                            predicates.add(builder.lessThan(root.<Date>get(criteria.getKey()), date));
                        } else {
                            predicates.add(builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString()));
                        }
                    }

                    
                    case GREATER_THAN_EQUAL -> {
                        if (criteria.getValue() instanceof Date date) {
                            predicates.add(builder.greaterThanOrEqualTo(root.<Date>get(criteria.getKey()), date));
                        } else {
                            predicates.add(builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
                        }
                    }

                    
                    case LESS_THAN_EQUAL -> {
                        if (criteria.getValue() instanceof Date date) {
                            predicates.add(builder.lessThanOrEqualTo(root.<Date>get(criteria.getKey()), date));
                        } else {
                            predicates.add(builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
                        }
                    }                
                
                    case IN -> predicates.add(root.get(criteria.getKey()).in(criteria.getValue()));
                }
            }
        }
        return builder.and(predicates.toArray(Predicate[]::new));
    }
}
