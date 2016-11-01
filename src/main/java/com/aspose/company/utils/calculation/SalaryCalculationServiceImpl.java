package com.aspose.company.utils.calculation;

import com.aspose.company.model.Supervisor;
import com.aspose.company.model.Worker;

import java.util.List;

/**
 * Created by serhii on 10/30/16.
 */
public class SalaryCalculationServiceImpl implements SalaryCalculationService {


    @Override
    public int calculateYearBenefit(int fixedSalary, double yearBenefitPercent, int fullYears, double limitOfYearBenefitPercent) {
        double benefitForAllFullWorkYears = yearBenefitPercent * fullYears;

        double benefitConsideringLimit = benefitForAllFullWorkYears > limitOfYearBenefitPercent ?
                limitOfYearBenefitPercent : benefitForAllFullWorkYears;

        double salaryWithBenefits = fixedSalary + fixedSalary * benefitConsideringLimit;

        return (int)Math.round(salaryWithBenefits);
    }

    @Override
    public int calculateTeamBenefitByLevel(List<Worker> workerList, int level) {

        if(level == 0 || (workerList == null || workerList.isEmpty())){
            return 0;
        }

        int amount = 0;

        for (Worker worker : workerList) {
            amount += worker.calculateSalary();

            if(worker instanceof Supervisor){
                amount += calculateTeamBenefitByLevel(((Supervisor) worker).getSubWorkers(), level - 1);
            }
        }

        return amount;
    }




}
