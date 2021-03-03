package org.example.Payroll.Service.imp;

import org.example.Payroll.model.Employee;
import org.example.Payroll.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> index(){

        List<Employee> employeeList = new ArrayList<>();
        employeeRepository.findAll()
        .forEach(employeeList::add);

        return employeeList;
    }

    public Employee getEmployee(String type){
        return  employeeRepository.findByType(type);

    }

    public void updateEmployee(Long id, double salary){
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if(employeeOptional.isPresent()){
            Employee employee = employeeOptional.get();
            employee.setSalary(salary);

            employeeRepository.save(employee);
        }


    }

}
