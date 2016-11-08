package com.asp.company.model;

import com.asp.company.utils.converter.DateUtils;
import com.asp.company.utils.file.PropertiesHolder;

import java.util.Date;

/**
 * Created by serhii on 10/29/16.
 */
public class Manager extends Supervisor {

    private static final double YEAR_BENEFIT_PERCENT =
            PropertiesHolder.getDoubleProperty("manager.year.benefit");

    private static final double BENEFIT_LIMIT_PERCENT =
            PropertiesHolder.getDoubleProperty("manager.year.benefit.limit");

    private static final double BENEFIT_FOR_SUBWORKERS_LIMIT_PERCENT =
            PropertiesHolder.getDoubleProperty("manager.subworkers.benefit");

    private static final int HIERARCHY_LIMIT_LEVEL =
            PropertiesHolder.getIntProperty("manager.consider.subworkers.level");

    public Manager(String name, int monthSalary, Date hireDate) {
        super(name, monthSalary, hireDate);
    }

    @Override
    public int calculateSalary() {
        double amountOfSubworkersSalary = getSalaryCalculationService().calculateTeamBenefitByLevel(getSubWorkers(),
                HIERARCHY_LIMIT_LEVEL);
        int benefitFromSubworkers = (int) Math.round(amountOfSubworkersSalary * BENEFIT_FOR_SUBWORKERS_LIMIT_PERCENT);

        int ownSalaryWithYearBenefits = getSalaryCalculationService().calculateYearBenefit(
                getMonthSalary(), YEAR_BENEFIT_PERCENT, DateUtils.getDiffYears(getHireDate(), new Date()),
                BENEFIT_LIMIT_PERCENT);

        return ownSalaryWithYearBenefits + benefitFromSubworkers;
    }
}
