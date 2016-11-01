package com.aspose.company.model;

import com.aspose.company.utils.calculation.SalaryCalculationService;
import com.aspose.company.utils.converter.DateUtils;

import java.util.Date;

/**
 * Created by serhii on 10/29/16.
 */
public class Employee extends Worker {

    public static final double YEAR_BENEFIT_PERCENT = 0.03;
    public static final double BENEFIT_LIMIT_PERCENT = 0.30;

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
