package com.aspose.company.utils.generator;

import com.aspose.company.exception.NoSupportedSubTypeException;
import com.aspose.company.model.Employee;
import com.aspose.company.model.Manager;
import com.aspose.company.model.Sales;
import com.aspose.company.model.Supervisor;
import com.aspose.company.service.CompanyService;
import com.aspose.company.utils.service_locator.ServiceLocator;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by serhii on 10/31/16.
 */
public final class DataGenerationUtils {

    public static AtomicInteger atomicLong = createInstance();

    public synchronized static AtomicInteger createInstance(){
        return new AtomicInteger(1);
    }

    public static int generateId(){
        return atomicLong.getAndIncrement();
    }


    public static Supervisor buildTreeAndSave() throws NoSupportedSubTypeException {

        CompanyService companyService = ServiceLocator.getBean(CompanyService.class);
        // 2 level
        Manager root = (Manager) companyService.addWorker(new Manager("root", 3000, new Date()));

        Sales sales1 = (Sales) companyService.addWorker(new Sales("sales1", 3000, new Date()));
        Manager manager1 = (Manager) companyService.addWorker(new Manager("manager1", 3000, new Date()));
        Employee employee1 = (Employee) companyService.addWorker(new Employee("empl1", 3000, new Date()));

        // 3 level
        Sales sales3 = (Sales) companyService.addWorker(new Sales("sales3", 3000, new Date()));
        Sales sales4 = (Sales) companyService.addWorker(new Sales("sales4", 3000, new Date()));
        Sales sales5 = (Sales) companyService.addWorker(new Sales("sales5", 3000, new Date()));

        Manager manager2 = (Manager) companyService.addWorker(new Manager("manager2", 3000, new Date()));
        Employee employee2 = (Employee) companyService.addWorker(new Employee("empl2", 3000, new Date()));

        // 4 level
        Manager manager3 = (Manager) companyService.addWorker(new Manager("manager3", 3000, new Date()));
        Manager manager4 = (Manager) companyService.addWorker(new Manager("manager4", 3000, new Date()));
        Sales sales6 = (Sales) companyService.addWorker(new Sales("sales6", 3000, new Date()));
        Manager manager5 = (Manager) companyService.addWorker(new Manager("manager5", 3000, new Date()));

        sales3.getSubWorkers().add(manager3);
        manager3.setSupervisor(sales3);

        sales3.getSubWorkers().add(manager4);
        manager4.setSupervisor(sales3);

        manager2.getSubWorkers().add(sales6);
        sales6.setSupervisor(manager2);

        manager2.getSubWorkers().add(manager5);
        manager5.setSupervisor(manager2);

        sales1.getSubWorkers().add(sales3);
        sales3.setSupervisor(sales1);

        sales1.getSubWorkers().add(sales4);
        sales4.setSupervisor(sales1);

        sales1.getSubWorkers().add(sales5);
        sales5.setSupervisor(sales1);

        manager1.getSubWorkers().add(manager2);
        manager2.setSupervisor(manager1);
        manager1.getSubWorkers().add(employee2);
        employee2.setSupervisor(manager1);

        root.getSubWorkers().add(sales1);
        sales1.setSupervisor(root);
        root.getSubWorkers().add(manager1);
        manager1.setSupervisor(root);
        root.getSubWorkers().add(employee1);
        employee1.setSupervisor(root);

        return root;
    }
}
