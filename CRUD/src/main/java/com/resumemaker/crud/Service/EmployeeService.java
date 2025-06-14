package com.resumemaker.crud.Service;

import com.resumemaker.crud.Entity.Employee;
import com.resumemaker.crud.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }
    public Employee getEmployeeById(long id){
        return employeeRepository.findById(id).orElseThrow(()->new RuntimeException("Employee Not Found"));

    }
    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    public Employee updateEmployee(Long id,Employee employeeDetails){
        Employee employee = getEmployeeById(id);
        employee.setName(employeeDetails.getName());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setSalary(employeeDetails.getSalary());
        employee.setEmail(employeeDetails.getDepartment());

        return employeeRepository.save(employee);
    }
    public void deleteEmployeeById(Long id){

        employeeRepository.deleteById(id);
    }
}
