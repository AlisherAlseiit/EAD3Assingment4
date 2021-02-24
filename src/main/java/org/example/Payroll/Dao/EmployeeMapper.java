package org.example.Payroll.Dao;

import org.example.Payroll.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper  implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {


        Employee employee = new Employee();

        employee.setId(resultSet.getInt("id"));
        employee.setType(resultSet.getString("type"));
        employee.setSalary(resultSet.getDouble("salary"));

        return employee;
    }
}
