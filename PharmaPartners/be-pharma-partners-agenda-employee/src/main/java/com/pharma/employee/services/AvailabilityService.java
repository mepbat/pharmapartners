package com.pharma.employee.services;

import com.pharma.employee.repositories.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvailabilityService {

    @Autowired
    AvailabilityRepository availabilityRepository;
}
