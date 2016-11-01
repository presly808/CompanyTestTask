package com.aspose.company.utils.calculation;

import com.aspose.company.model.Worker;

import java.util.List;

/**
 * Created by serhii on 10/29/16.
 */
public interface SalaryCalculationService {

    int calculateYearBenefit(int fixedSalary, double yearBenefitPercent, int fullYears, double limitOfYearBenefitPercent);

    int calculateTeamBenefitByLevel(List<Worker> root, int level);

    int calculateTeamBenefitAllLevels(List<Worker> workerList);
}
