package com.asp.company.utils.service_locator;

import com.asp.company.exception.BeanTypeException;
import com.asp.company.exception.NoBeanFoundException;
import com.asp.company.model.Worker;
import com.asp.company.utils.calculation.SalaryCalculationService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by serhii on 10/31/16.
 */
public class TestServiceLocator {

    @Test
    public void testGetBeanByName() {
        try {
            SalaryCalculationService salaryCalculationService = ServiceLocator.getBean("salaryCalculatorService", SalaryCalculationService.class);
            Assert.assertNotNull(salaryCalculationService);
        } catch (NoBeanFoundException e) {
            e.printStackTrace();
        } catch (BeanTypeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetBeanByType() {
        try {
            SalaryCalculationService salaryCalculationService = ServiceLocator.getBean(SalaryCalculationService.class);
            Assert.assertNotNull(salaryCalculationService);
        } catch (NoBeanFoundException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = NoBeanFoundException.class)
    public void testGetBeanNoInContext() throws BeanTypeException, NoBeanFoundException {
        Worker salaryCalculationService = ServiceLocator.getBean(Worker.class);
    }

    @Test(expected = BeanTypeException.class)
    public void testGetBeanBeanTypeException() throws BeanTypeException, NoBeanFoundException {
        Worker salaryCalculationService = ServiceLocator.getBean("salaryCalculatorService", Worker.class);
    }

}
