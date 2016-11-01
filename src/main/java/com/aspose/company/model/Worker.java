package com.aspose.company.model;

import com.aspose.company.utils.calculation.SalaryCalculationService;
import com.aspose.company.utils.service_locator.ServiceLocator;

import java.util.Date;

/**
 * Created by serhii on 10/29/16.
 */
public abstract class Worker implements Employable {

    private int id;
    private String name;
    private int monthSalary = 3000;
    private Date hireDate;

    private Supervisor supervisor;

    private SalaryCalculationService salaryCalculationService =
            ServiceLocator.getBean(SalaryCalculationService.class);

    public Worker() {
    }

    public Worker(String name, int monthSalary, Date hireDate) {
        this.name = name;
        this.monthSalary = monthSalary;
        this.hireDate = hireDate;
    }


    public Worker(int id, String name, int monthSalary, Date hireDate) {
        this.id = id;
        this.name = name;
        this.monthSalary = monthSalary;
        this.hireDate = hireDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getMonthSalary() {
        return monthSalary;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public SalaryCalculationService getSalaryCalculationService() {
        return salaryCalculationService;
    }

    @Override
    public String toString() {
        return "Worker{" + " type " + getClass() + ", " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", monthSalary=" + monthSalary +
                ", hireDate=" + hireDate +
                ", supervisor=" + supervisor +
                '}';
    }
}
