package com.aspose.company.utils.calculation;

import com.aspose.company.model.*;
import com.aspose.company.utils.converter.DateUtils;
import com.aspose.company.utils.service_locator.ServiceLocator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;

/**
 * Created by serhii on 10/31/16.
 */
public class SalaryCalculationServiceTest {

    private SalaryCalculationService calculationService;

    @Before
    public void setUpData(){
        calculationService = ServiceLocator.getBean(SalaryCalculationService.class);
    }

    @Test
    public void calculateSalaryWithYearBenefit(){

        int salary = 3000;
        int fullYears = 3;
        double yearBenefitPercent = Employee.YEAR_BENEFIT_PERCENT;
        double limitOfYearBenefitsPercent = Employee.BENEFIT_LIMIT_PERCENT;

        // yearBen = 3000 * 0.03 = 90
        // forThreeYears = 90 * 3 = 270
        // sal + forThreeYears = 3270
        int expected = (int)Math.round(salary + (salary * yearBenefitPercent) * fullYears);

        int salaryWithYearsBenefits =
                calculationService.calculateYearBenefit(
                        salary, yearBenefitPercent, fullYears, limitOfYearBenefitsPercent);

        Assert.assertThat(salaryWithYearsBenefits, is(expected));
    }

    @Test
    public void calculateSalaryWithYearBenefitOverflow(){

        int salary = 3000;
        int fullYears = 20;
        double yearBenefitPercent = Employee.YEAR_BENEFIT_PERCENT;
        double limitOfYearBenefitsPercent = Employee.BENEFIT_LIMIT_PERCENT;

        // yearBen = 3000 * 0.03 = 90
        // for20Years = 90 * 20 = 1800
        // sal + for20Years = 3000 + 1800
        // for20Years benefit was overflowed, max benefit 30% (3000*0.3) = 900 max benefit
        // then we will have 3900
        double yearsBenefit = (salary * yearBenefitPercent) * fullYears;
        double maxBenefit = salary * Employee.BENEFIT_LIMIT_PERCENT;

        int expected = (int)Math.round(salary + (yearsBenefit > maxBenefit ? maxBenefit : yearsBenefit));

        int salaryWithYearsBenefits =
                calculationService.calculateYearBenefit(
                        salary, yearBenefitPercent, fullYears, limitOfYearBenefitsPercent);

        Assert.assertThat(salaryWithYearsBenefits, is(expected));
    }



    @Test
    public void calculateHierarchyTwoLevels(){

        EmployeeForTest root = new EmployeeForTest();

        EmployeeForTest employee1 = new EmployeeForTest();
        EmployeeForTest employee2 = new EmployeeForTest();
        EmployeeForTest employee3 = new EmployeeForTest();

        EmployeeForTest employee4 = new EmployeeForTest();
        EmployeeForTest employee5 = new EmployeeForTest();
        EmployeeForTest employee6 = new EmployeeForTest();
        EmployeeForTest employee7 = new EmployeeForTest();
        EmployeeForTest employee8 = new EmployeeForTest();

        employee1.getSubWorkers().add(employee4);
        employee1.getSubWorkers().add(employee5);

        employee2.getSubWorkers().add(employee6);
        employee2.getSubWorkers().add(employee7);

        employee3.getSubWorkers().add(employee8);

        root.getSubWorkers().add(employee1);
        root.getSubWorkers().add(employee2);
        root.getSubWorkers().add(employee3);

        /*
        * root 3000
        *   emp1 3000
        *       em4 3000
        *       em5 3000
        *   em2 3000
        *       em6 3000
        *       em7 3000
        *   em3 3000
        *       em8 3000
        *
        *       expected two first levels 12000
        * */

        Assert.assertThat("salaryWithHierarchyLevel1",
                calculationService.calculateTeamBenefitByLevel(Arrays.asList(root), 2), is(equalTo(12000)));
    }

    @Test
    public void testHierarchyFull(){

        EmployeeForTest root = new EmployeeForTest();

        EmployeeForTest employee1 = new EmployeeForTest();
        EmployeeForTest employee2 = new EmployeeForTest();
        EmployeeForTest employee3 = new EmployeeForTest();

        EmployeeForTest employee4 = new EmployeeForTest();
        EmployeeForTest employee5 = new EmployeeForTest();
        EmployeeForTest employee6 = new EmployeeForTest();
        EmployeeForTest employee7 = new EmployeeForTest();
        EmployeeForTest employee8 = new EmployeeForTest();

        employee1.getSubWorkers().add(employee4);
        employee1.getSubWorkers().add(employee5);

        employee2.getSubWorkers().add(employee6);
        employee2.getSubWorkers().add(employee7);

        employee3.getSubWorkers().add(employee8);

        root.getSubWorkers().add(employee1);
        root.getSubWorkers().add(employee2);
        root.getSubWorkers().add(employee3);

        /*
        * root 3000
        *   emp1 3000
        *       em4 3000
        *       em5 3000
        *   em2 3000
        *       em6 3000
        *       em7 3000
        *   em3 3000
        *       em8 3000
        *
        *       expected all level 27000
        * */

        Assert.assertThat("salaryWithHierarchyLevel1",
                calculationService.calculateTeamBenefitByLevel(Arrays.asList(root), -1), is(equalTo(27000)));
    }

    /**
     * class for simplify test expected result, we exclude other conditions, for getting clear(indented) result
     *
     * */
    static class EmployeeForTest extends Supervisor {

        public EmployeeForTest(){
            super("default", 3000, new Date());
        }

        public EmployeeForTest(String name, int monthSalary, Date hireDate) {
            super(name, monthSalary, hireDate);
        }

        @Override
        public int calculateSalary() {
            return getMonthSalary();
        }
    }


}
