package org.example.Payroll.Service;

import org.example.Payroll.Dao.EmployeeDao;
import org.example.Payroll.event.SalaryChangeEvent;
import org.example.Payroll.model.EmployeeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service("calculator")
public class SalaryCalculatorService implements ApplicationEventPublisherAware {
    private static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
    private double totalCostOfNumOfSales;
    private double CommissionPercentage;

    private int workedHour;
    private double HourlySalary;
    private int overtimeHour;


    @Value("${employeeData.baseSalary}")
    private double baseSalary;
    @Value("${employeeData.totalCost}")
    private double SCTotalCostOfNumOfSales;
    @Value("${employeeData.SCPercentage}")
    private double SCPercentage;

    private double fixedMonthlySalary;

    private ApplicationEventPublisher eventPublisher;
    private final EmployeeDao employeeDao;

    @Autowired
    public SalaryCalculatorService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }


    public void update(EmployeeType employeeType, int id, String type) throws IOException {

        switch (employeeType){
            case SALARIEDCOMMISSION:
                System.out.println("Enter new base salary for Employee");
                double base = Double.parseDouble(read.readLine());
                baseSalary = base;
                double salary = baseSalary + (SCTotalCostOfNumOfSales * SCPercentage);
                employeeDao.update(id,salary);

                this.eventPublisher.publishEvent(new SalaryChangeEvent(this, employeeDao.show(type)));
            break;

            default:
                System.out.println("We want to change SalariedCommission");
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
