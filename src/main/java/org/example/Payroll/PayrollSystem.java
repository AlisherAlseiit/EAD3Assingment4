package org.example.Payroll;

import org.example.Payroll.Config.SpringConfig;
import org.example.Payroll.Service.SalaryCalculatorService;
import org.example.Payroll.Service.imp.EmployeeServiceImpl;
import org.example.Payroll.model.Employee;
import org.example.Payroll.model.EmployeeType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class PayrollSystem {

    private static Boolean bool = true;
    private static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class
        );


        SalaryCalculatorService salaryCalculatorService = context.getBean("calculator", SalaryCalculatorService.class);

        EmployeeServiceImpl employeeService = context.getBean("employeeServiceImpl", EmployeeServiceImpl.class);



        while (bool){
            System.out.println("1. To Return all Employees");
            System.out.println("2. To return 1 Employee ");
            System.out.println("3. To update");
            System.out.println("0. To Quit");
            String choice = read.readLine();

            switch (choice){
                case "1":
                    for(Employee employee : employeeService.index())
                    System.out.println(employee.getId() + "," + employee.getType() + "," + employee.getSalary());

                    break;
                case "2":
                    System.out.println("PLS Enter employee type");
                    String type = read.readLine();
                    System.out.println("id: " + employeeService.getEmployee(type).getId());
                    System.out.println("type: " + employeeService.getEmployee(type).getType());
                    System.out.println("salary: " + employeeService.getEmployee(type).getSalary());
                    break;
                case "3":
                    System.out.println("Please Enter type of Employee");
                    String t = read.readLine();
                    if(t.equals("SalariedCommission"))
                    salaryCalculatorService.update(EmployeeType.SALARIEDCOMMISSION, employeeService.getEmployee(t).getId(), employeeService.getEmployee(t).getType());
                    else if (t.equals("Salaried"))
                        salaryCalculatorService.update(EmployeeType.SALARIED, employeeService.getEmployee(t).getId(), employeeService.getEmployee(t).getType());
                    else if (t.equals("Hourly"))
                        salaryCalculatorService.update(EmployeeType.HOURLY, employeeService.getEmployee(t).getId(), employeeService.getEmployee(t).getType());
                    else if (t.equals("Commission"))
                        salaryCalculatorService.update(EmployeeType.COMMISSION, employeeService.getEmployee(t).getId(), employeeService.getEmployee(t).getType());

                    break;
                default:
                    bool = false;

            }
        }


    }
}
