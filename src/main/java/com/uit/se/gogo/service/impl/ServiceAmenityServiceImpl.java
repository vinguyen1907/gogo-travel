package com.uit.se.gogo.service.impl;

import com.uit.se.gogo.entity.ServiceAmenity;
import com.uit.se.gogo.repository.ServiceAmenityRepository;
import com.uit.se.gogo.service.ServiceAmenityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceAmenityServiceImpl implements ServiceAmenityService {

    private final ServiceAmenityRepository serviceAmenityRepository;

    @Override
    public ServiceAmenity save(ServiceAmenity serviceAmenity) {
        return serviceAmenityRepository.save(serviceAmenity);
    }
}
