package com.aspose.company.service;

import com.aspose.company.db.AppDB;
import com.aspose.company.exception.NoWorkerFoundException;
import com.aspose.company.model.Employee;
import com.aspose.company.model.Manager;
import com.aspose.company.model.Sales;
import com.aspose.company.model.Worker;
import com.aspose.company.utils.service_locator.ServiceLocator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Created by serhii on 11/1/16.
 */
public class CompanyServiceTest {


    private CompanyService companyService;

    @Before
    public void setUp(){
        companyService = ServiceLocator.getBean(CompanyService.class);
        AppDB appDB = ServiceLocator.getBean(AppDB.class);

        appDB.managerMap = new ConcurrentHashMap<>();
        appDB.salesMap = new ConcurrentHashMap<>();
        appDB.employeeMap = new ConcurrentHashMap<>();
    }

    @After
    public void tearDown(){
        AppDB appDB = ServiceLocator.getBean(AppDB.class);

        appDB.managerMap = null;
        appDB.salesMap = null;
        appDB.employeeMap = null;
    }

    @Test
    public void testAddEmployee() throws Exception {
        Employee employee = new Employee("empl1", 1000, new Date());
        Worker created1 = companyService.addWorker(employee);
        Assert.assertThat(created1, is(instanceOf(Employee.class)));
        Assert.assertThat(created1.getId(), is(not(0)));

        Sales sales = new Sales("empl1", 1000, new Date());
        Worker created2 = companyService.addWorker(sales);
        Assert.assertThat(created2, is(instanceOf(Sales.class)));
        Assert.assertThat(created2.getId(), is(not(0)));

        Manager manager = new Manager("empl1", 1000, new Date());
        Worker created3 = companyService.addWorker(manager);
        Assert.assertThat(created3, is(instanceOf(Manager.class)));
        Assert.assertThat(created3.getId(), is(not(0)));
    }

    @Test
    public void testGetById() throws Exception {
        Employee employee = new Employee("empl1", 1000, new Date());
        Worker created1 = companyService.addWorker(employee);

        Worker found = companyService.getById(created1.getId());
        Assert.assertThat(found, is(notNullValue()));
    }

    @Test(expected = NoWorkerFoundException.class)
    public void testGetByIdNoFound() throws Exception {
        Employee employee = new Employee("empl1", 1000, new Date());
        Worker created1 = companyService.addWorker(employee);

        Worker found = companyService.getById(-5);

    }

    @Test
    public void testSearchByName() throws Exception {
        Employee employee7 = new Employee("Evgen", 1000, new Date());
        Worker created7 = companyService.addWorker(employee7);

        Employee employee8 = new Employee("Bogdan", 1000, new Date());
        Worker created8 = companyService.addWorker(employee8);

        Employee employee = new Employee("Anton", 1000, new Date());
        Worker created1 = companyService.addWorker(employee);

        Sales sales = new Sales("Andrey", 1000, new Date());
        Worker created2 = companyService.addWorker(sales);

        Manager manager = new Manager("Anatoliy", 1000, new Date());
        Worker created3 = companyService.addWorker(manager);

        List<Worker> all = companyService.searchByName("An");
        Assert.assertThat(all, hasSize(3));
        Assert.assertEquals(3, all.size());
    }

    @Test
    public void testGetAllWorkers() throws Exception {
        Employee employee7 = new Employee("Evgen", 1000, new Date());
        Worker created7 = companyService.addWorker(employee7);

        Employee employee8 = new Employee("Bogdan", 1000, new Date());
        Worker created8 = companyService.addWorker(employee8);

        Employee employee = new Employee("Anton", 1000, new Date());
        Worker created1 = companyService.addWorker(employee);

        Sales sales = new Sales("Andrey", 1000, new Date());
        Worker created2 = companyService.addWorker(sales);

        Manager manager = new Manager("Anatoliy", 1000, new Date());
        Worker created3 = companyService.addWorker(manager);

        List<Worker> workers = companyService.getAllWorkers();

        Assert.assertThat(workers, hasSize(5));

    }

    @Test
    public void testAddSubworker() throws Exception {

    }

    @Test
    public void testGetWorkerType() throws Exception {

    }

    @Test
    public void testCalculateMonthSalary() throws Exception {

    }

    @Test
    public void testCalculateMonthSalaryAllWorkers() throws Exception {

    }
}