package com.pharma.employee.controllers;

import com.pharma.employee.services.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/availabilities")
public class AvailabilityController {
    @Autowired
    AvailabilityService availabilityService;
}
