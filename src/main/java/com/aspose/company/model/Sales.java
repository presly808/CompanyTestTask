package com.aspose.company.model;

import com.aspose.company.utils.converter.DateUtils;

import java.util.Date;

/**
 * Created by serhii on 10/29/16.
 */
public class Sales extends Supervisor {

    private static final double YEAR_BENEFIT_EXPERIENCE_PERCENT = 0.01;
    private static final double BENEFIT_EXPERIENCE_LIMIT_PERCENT = 0.35;
    private static final double HIERARCHY_SUBWORKER_BENEFIT_PERCENT = 0.003;

    public Sales(String name, int monthSalary, Date hireDate) {
        super(name, monthSalary, hireDate);
    }

    @Override
    public int calculateSalary() {
        double amountOfSubworkersSalary = getSalaryCalculationService().calculateTeamBenefitAllLevels(getSubWorkers());
        int benefitFromSubworkers = (int) Math.round(amountOfSubworkersSalary * HIERARCHY_SUBWORKER_BENEFIT_PERCENT);

        int ownSalaryWithYearBenefits = getSalaryCalculationService().calculateYearBenefit(
                getMonthSalary(), BENEFIT_EXPERIENCE_LIMIT_PERCENT, DateUtils.getDiffYears(getHireDate(), new Date()),
                BENEFIT_EXPERIENCE_LIMIT_PERCENT);

        return ownSalaryWithYearBenefits + benefitFromSubworkers;
    }
}
