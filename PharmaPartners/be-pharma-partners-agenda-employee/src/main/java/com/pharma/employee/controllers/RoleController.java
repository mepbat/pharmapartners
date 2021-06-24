package com.pharma.employee.controllers;

import com.pharma.employee.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    RoleService roleService;
}
