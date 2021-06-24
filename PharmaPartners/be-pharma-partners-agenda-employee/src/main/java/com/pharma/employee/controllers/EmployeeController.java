package com.pharma.employee.controllers;

import com.pharma.employee.models.Employee;
import com.pharma.employee.models.Gender;
import com.pharma.employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<Employee>> all() {
        return new ResponseEntity<>(employeeService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.findbyId(id);
        if(employee.isEmpty()){
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee.get(), HttpStatus.OK);
    }

    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<Employee> add(Employee employee) {
        return new ResponseEntity<>(employeeService.add(employee), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return new ResponseEntity<>("Employee deleted", HttpStatus.OK);
    }

    @PutMapping(value = "/update", produces = "application/json")
    public ResponseEntity<Employee> put(Employee employee) {
        return new ResponseEntity<>(employeeService.add(employee), HttpStatus.OK);
    }

}
