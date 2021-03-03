package org.example.Payroll.repository;

import org.example.Payroll.model.Employee;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long> {


    @Query(value = "SELECT * FROM Employee e WHERE e.type=?1", nativeQuery = true)
    Employee findByType(String type);
}
