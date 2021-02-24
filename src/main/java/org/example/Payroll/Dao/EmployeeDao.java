package org.example.Payroll.Dao;

import org.example.Payroll.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> index(){
        return jdbcTemplate.query("SELECT * FROM Employee", new BeanPropertyRowMapper<>(Employee.class));
    }

    public Employee show(String type){
        return  jdbcTemplate.query("SELECT * FROM Employee WHERE type=?", new Object[]{type}, new BeanPropertyRowMapper<>(Employee.class))
                .stream().findAny().orElse(null);
    }

    public void update(int id, double salary){
        jdbcTemplate.update("UPDATE Employee SET salary=? WHERE id=?", salary, id);
    }


}
