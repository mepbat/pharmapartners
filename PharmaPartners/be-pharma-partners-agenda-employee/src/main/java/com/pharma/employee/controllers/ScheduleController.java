package com.pharma.employee.controllers;

import com.pharma.employee.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;
}
