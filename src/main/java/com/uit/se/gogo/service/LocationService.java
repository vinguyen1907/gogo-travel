package com.uit.se.gogo.service;
import com.uit.se.gogo.request.LocationRequest;
import com.uit.se.gogo.response.LocationResponse;

import java.util.List;


public interface LocationService {
    LocationResponse findById(String id);
    List<LocationResponse> findAll();
    LocationResponse createLocation(LocationRequest request);
}
