package com.aspose.company.model;

import com.aspose.company.utils.converter.DateUtils;
import com.aspose.company.utils.file.PropertiesHolder;

import java.util.Date;

/**
 * Created by serhii on 10/29/16.
 */
public class Sales extends Supervisor {

    private static final double YEAR_BENEFIT_EXPERIENCE_PERCENT =
            PropertiesHolder.getDoubleProperty("sales.year.benefit");

    private static final double BENEFIT_EXPERIENCE_LIMIT_PERCENT =
            PropertiesHolder.getDoubleProperty("sales.year.benefit.limit");

    private static final double HIERARCHY_SUBWORKER_BENEFIT_PERCENT =
            PropertiesHolder.getDoubleProperty("sales.subworkers.benefit");

    private static final int HIERARCHY_LIMIT_LEVEL =
            PropertiesHolder.getIntProperty("sales.consider.subworkers.level");

    public Sales(String name, int monthSalary, Date hireDate) {
        super(name, monthSalary, hireDate);
    }

    @Override
    public int calculateSalary() {
        double amountOfSubworkersSalary = getSalaryCalculationService().calculateTeamBenefitByLevel(getSubWorkers(),
                HIERARCHY_LIMIT_LEVEL);
        int benefitFromSubworkers = (int) Math.round(amountOfSubworkersSalary * HIERARCHY_SUBWORKER_BENEFIT_PERCENT);

        int ownSalaryWithYearBenefits = getSalaryCalculationService().calculateYearBenefit(
                getMonthSalary(), YEAR_BENEFIT_EXPERIENCE_PERCENT, DateUtils.getDiffYears(getHireDate(), new Date()),
                BENEFIT_EXPERIENCE_LIMIT_PERCENT);

        return ownSalaryWithYearBenefits + benefitFromSubworkers;
    }
}
