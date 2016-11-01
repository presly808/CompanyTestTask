package com.aspose.company.model;

import com.aspose.company.utils.converter.DateUtils;

import java.util.Date;

/**
 * Created by serhii on 10/29/16.
 */
public class Manager extends Supervisor {

    private static final double YEAR_BENEFIT_PERCENT = 0.05;
    private static final double BENEFIT_LIMIT_PERCENT = 0.40;
    private static final double BENEFIT_FOR_SUBWORKERS_LIMIT_PERCENT = 0.005;

    public Manager(String name, int monthSalary, Date hireDate) {
        super(name, monthSalary, hireDate);
    }

    @Override
    public int calculateSalary() {
        double amountOfSubworkersSalary = getSalaryCalculationService().calculateTeamBenefitByLevel(getSubWorkers(), 1);
        int benefitFromSubworkers = (int) Math.round(amountOfSubworkersSalary * BENEFIT_FOR_SUBWORKERS_LIMIT_PERCENT);

        int ownSalaryWithYearBenefits = getSalaryCalculationService().calculateYearBenefit(
                getMonthSalary(), YEAR_BENEFIT_PERCENT, DateUtils.getDiffYears(getHireDate(), new Date()),
                BENEFIT_LIMIT_PERCENT);

        return ownSalaryWithYearBenefits + benefitFromSubworkers;
    }
}
