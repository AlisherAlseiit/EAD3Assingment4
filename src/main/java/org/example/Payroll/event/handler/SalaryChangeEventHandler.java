package org.example.Payroll.event.handler;

import org.example.Payroll.event.SalaryChangeEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SalaryChangeEventHandler  implements ApplicationListener<SalaryChangeEvent> {

    @Override
    public void onApplicationEvent(SalaryChangeEvent salaryChangeEvent) {
        System.out.println("---------------------------------------------------------");
        System.out.println("SalaryChangeEventHandler.onApplicationEvent");
        System.out.println("changed salary for: " + salaryChangeEvent.getEmployee().getType());
    }
}
