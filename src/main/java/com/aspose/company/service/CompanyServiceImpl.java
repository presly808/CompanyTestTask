package com.aspose.company.service;

import com.aspose.company.dao.EmployeeDao;
import com.aspose.company.dao.ManagerDao;
import com.aspose.company.dao.SalesDao;
import com.aspose.company.exception.NoSupportedSubTypeException;
import com.aspose.company.exception.NoWorkerFoundException;
import com.aspose.company.exception.WorkerIsNotSupervisorException;
import com.aspose.company.model.*;
import com.aspose.company.utils.service_locator.ServiceLocator;
import com.aspose.company.utils.visualization.PrintUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by serhii on 10/31/16.
 */
public class CompanyServiceImpl implements CompanyService {

    private EmployeeDao employeeDao = ServiceLocator.getBean(EmployeeDao.class);
    private ManagerDao managerDao = ServiceLocator.getBean(ManagerDao.class);
    private SalesDao salesDao = ServiceLocator.getBean(SalesDao.class);

    public CompanyServiceImpl() {
    }

    @Override
    public Worker addWorker(Worker newWorker) throws NoSupportedSubTypeException {

        if(newWorker.getClass() == Employee.class){
            return employeeDao.create((Employee)newWorker);
        } else if(newWorker.getClass() == Manager.class){
            return managerDao.create((Manager) newWorker);
        } else if(newWorker.getClass() == Sales.class){
            return salesDao.create((Sales) newWorker);
        }

        throw new NoSupportedSubTypeException("Type " + newWorker.getName() + " is not supported");
    }

    @Override
    public Worker getById(int id) throws NoWorkerFoundException {
        Employee foundEmpl = employeeDao.getById(id);
        Sales foundSales = salesDao.getById(id);
        Manager foundManager = managerDao.getById(id);


        if(foundEmpl != null){
            return foundEmpl;
        } else if(foundSales != null){
            return foundSales;
        } else if(foundManager != null){
            return foundManager;
        }

        throw new NoWorkerFoundException("no worker with id " + id);
    }

    @Override
    public List<Worker> searchByName(String name) {

        Stream<Employee> streamEmpl = employeeDao.getAll().stream();
        Stream<Sales> streamSales = salesDao.getAll().stream();
        Stream<Manager> streamManager = managerDao.getAll().stream();

        Stream<Worker> all = Stream.concat(Stream.concat(streamEmpl, streamSales), streamManager);

        return all.filter((el) -> el.getName().contains(name)).collect(Collectors.toList());
    }

    @Override
    public List<Worker> getAllWorkers() {
        Stream<Employee> streamEmpl = employeeDao.getAll().stream();
        Stream<Sales> streamSales = salesDao.getAll().stream();
        Stream<Manager> streamManager = managerDao.getAll().stream();

        Stream<Worker> all = Stream.concat(Stream.concat(streamEmpl, streamSales), streamManager);

        return all.collect(Collectors.toList());
    }

    @Override
    public Supervisor addSubworker(int supervisorId, int subworkerId) throws NoWorkerFoundException, WorkerIsNotSupervisorException {
        Worker supervisor = getById(supervisorId);

        if(!(supervisor instanceof Supervisor)){
            throw new WorkerIsNotSupervisorException("found worker is " + supervisor.getClass().getName());
        }

        Supervisor castedSupervisor = (Supervisor) supervisor;

        Worker subWorker = getById(subworkerId);

        castedSupervisor.getSubWorkers().add(subWorker);

        subWorker.setSupervisor(castedSupervisor);

        return castedSupervisor;
    }

    @Override
    public String getWorkerType(int workerId) throws NoWorkerFoundException {
        Worker worker = getById(workerId);
        return worker.getClass().getName();
    }

    @Override
    public int calculateMonthSalary(int workerId) throws NoWorkerFoundException {
        Worker worker = getById(workerId);
        return worker.calculateSalary();
    }

    @Override
    public int calculateMonthSalaryAllWorkers() {
        Stream<Employee> streamEmpl = employeeDao.getAll().stream();
        Stream<Sales> streamSales = salesDao.getAll().stream();
        Stream<Manager> streamManager = managerDao.getAll().stream();

        Stream<Worker> all = Stream.concat(Stream.concat(streamEmpl, streamSales), streamManager);

        return all.map(Employable::calculateSalary).collect(Collectors.summingInt((salary) -> salary));
    }

    @Override
    public String getTreeView(int workerId) throws NoWorkerFoundException {
        Worker worker = getById(workerId);
        return PrintUtils.getTreeView(Arrays.asList(worker), -1);
    }
}
