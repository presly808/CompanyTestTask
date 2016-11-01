package com.aspose.company.model;

import com.aspose.company.utils.calculation.SalaryCalculationService;
import com.aspose.company.utils.converter.DateUtils;
import com.aspose.company.utils.file.PropertiesHolder;

import java.util.Date;

/**
 * Created by serhii on 10/29/16.
 */
public class Employee extends Worker {

    public static final double YEAR_BENEFIT_PERCENT =
            PropertiesHolder.getDoubleProperty("employee.year.benefit");

    public static final double BENEFIT_LIMIT_PERCENT =
            PropertiesHolder.getDoubleProperty("employee.year.benefit.limit");

    public Employee(String name, int monthSalary, Date hireDate) {
        super(name, monthSalary, hireDate);
    }

    @Override
    public int calculateSalary() {
        SalaryCalculationService salaryCalculationService = getSalaryCalculationService();
        return salaryCalculationService.calculateYearBenefit(
                getMonthSalary(), YEAR_BENEFIT_PERCENT, DateUtils.getDiffYears(getHireDate(), new Date()), BENEFIT_LIMIT_PERCENT);
    }
}
