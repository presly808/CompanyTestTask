package com.asp.company.service;

import com.asp.company.db.AppDB;
import com.asp.company.exception.NoSupportedSubTypeException;
import com.asp.company.exception.NoWorkerFoundException;
import com.asp.company.exception.WorkerIsNotSupervisorException;
import com.asp.company.model.*;
import com.asp.company.utils.generator.DataGenerationUtils;
import com.asp.company.utils.service_locator.ServiceLocator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.hamcrest.Matchers.*;

/**
 * Created by serhii on 11/1/16.
 */
public class CompanyServiceTest {


    private CompanyService companyService;

    @Before
    public void setUp() {
        companyService = ServiceLocator.getBean(CompanyService.class);
        AppDB appDB = ServiceLocator.getBean(AppDB.class);

        appDB.managerMap = new ConcurrentHashMap<>();
        appDB.salesMap = new ConcurrentHashMap<>();
        appDB.employeeMap = new ConcurrentHashMap<>();
    }

    @After
    public void tearDown() {
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
        Employee employee = new Employee("Anton", 1000, new Date());
        Worker created1 = companyService.addWorker(employee);

        Sales sales = new Sales("Andrey", 1000, new Date());
        Worker created2 = companyService.addWorker(sales);

        Manager manager = new Manager("Anatoliy", 1000, new Date());
        Worker created3 = companyService.addWorker(manager);

        Supervisor supervisor = companyService.addSubworker(created3.getId(), created1.getId());

        Assert.assertThat(supervisor.getSubWorkers(), contains(employee));
    }

    @Test(expected = NoWorkerFoundException.class)
    public void testAddSubworkerNoWorkerFound() throws Exception {
        Employee employee = new Employee("Anton", 1000, new Date());
        Worker created1 = companyService.addWorker(employee);

        Sales sales = new Sales("Andrey", 1000, new Date());
        Worker created2 = companyService.addWorker(sales);

        Manager manager = new Manager("Anatoliy", 1000, new Date());
        Worker created3 = companyService.addWorker(manager);

        Supervisor supervisor = companyService.addSubworker(-6, created1.getId());

    }

    @Test(expected = WorkerIsNotSupervisorException.class)
    public void testAddSubworkerWrongTypeWorker() throws Exception {
        Employee employee = new Employee("Anton", 1000, new Date());
        Worker created1 = companyService.addWorker(employee);

        Sales sales = new Sales("Andrey", 1000, new Date());
        Worker created2 = companyService.addWorker(sales);

        Manager manager = new Manager("Anatoliy", 1000, new Date());
        Worker created3 = companyService.addWorker(manager);

        Supervisor supervisor = companyService.addSubworker(created1.getId(), created3.getId());

    }

    @Test
    public void testGetWorkerType() throws Exception {
        Employee employee = new Employee("Anton", 1000, new Date());
        Worker created1 = companyService.addWorker(employee);
        String type = companyService.getWorkerType(created1.getId());
        Assert.assertThat(type, equalTo(employee.getClass().getName()));
    }

    @Test
    public void getTreeView() throws NoSupportedSubTypeException {


        // 1 level
        Manager root = (Manager) DataGenerationUtils.buildTreeAndSave();

        try {
            String treeView = companyService.getTreeView(root.getId());
            System.out.println(treeView);
            Assert.assertThat(treeView, not(isEmptyOrNullString()));
        } catch (NoWorkerFoundException e) {
            e.printStackTrace();
        }

    }

    /* Expected

     type:Manager,name:root,salary:3000,calculatedSalary:3045
        type:Sales,name:sales1,salary:3000,calculatedSalary:3045
            type:Sales,name:sales3,salary:3000,calculatedSalary:3018
                type:Manager,name:manager3,salary:3000,calculatedSalary:3000
                type:Manager,name:manager4,salary:3000,calculatedSalary:3000
            type:Sales,name:sales4,salary:3000,calculatedSalary:3000
            type:Sales,name:sales5,salary:3000,calculatedSalary:3000
        type:Manager,name:manager1,salary:3000,calculatedSalary:3030
            type:Manager,name:manager2,salary:3000,calculatedSalary:3030
                type:Sales,name:sales6,salary:3000,calculatedSalary:3000
                type:Manager,name:manager5,salary:3000,calculatedSalary:3000
            type:Employee,name:empl2,salary:3000,calculatedSalary:3000
        type:Employee,name:empl1,salary:3000,calculatedSalary:3000
    */
    @Test
    public void testCalculateMonthSalary() throws Exception {
        // 1 level
        Supervisor supervisor = DataGenerationUtils.buildTreeAndSave();
        Assert.assertThat(companyService.calculateMonthSalary(supervisor.getId()), is(3045));
    }

    @Test
    public void testCalculateMonthSalaryAllWorkers() throws Exception {
        Supervisor supervisor = DataGenerationUtils.buildTreeAndSave();
        supervisor = (Supervisor) companyService.addWorker(supervisor);
        System.out.println(companyService.getTreeView(supervisor.getId()));
        Assert.assertThat(companyService.calculateMonthSalaryAllWorkers(), is(42213));
    }
}