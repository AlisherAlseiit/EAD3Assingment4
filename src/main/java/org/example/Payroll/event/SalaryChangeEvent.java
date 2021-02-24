package org.example.Payroll.event;

import org.example.Payroll.model.Employee;
import org.springframework.context.ApplicationEvent;

public class SalaryChangeEvent  extends ApplicationEvent {

    private Employee employee;

    public SalaryChangeEvent(Object source, Employee employee) {
        super(source);
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
