package com.pharma.employee.services;

import com.pharma.employee.models.Employee;
import com.pharma.employee.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

    public Employee add(Employee employee){
         return employeeRepository.save(employee);
    }

    public void delete(Long id){
        employeeRepository.deleteById(id);
    }

    public Optional<Employee> findbyId(Long id){
        return employeeRepository.findById(id);
    }
}
