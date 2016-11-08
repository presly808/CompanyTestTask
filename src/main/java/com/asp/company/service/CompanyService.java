package com.asp.company.service;

import com.asp.company.exception.NoSupportedSubTypeException;
import com.asp.company.exception.NoWorkerFoundException;
import com.asp.company.exception.WorkerIsNotSupervisorException;
import com.asp.company.model.Supervisor;
import com.asp.company.model.Worker;

import java.util.List;

/**
 * Created by serhii on 10/29/16.
 */
public interface CompanyService {

    Worker addWorker(Worker newWorker) throws NoSupportedSubTypeException;

    Worker getById(int id) throws NoWorkerFoundException;

    List<Worker> searchByName(String name);

    List<Worker> getAllWorkers();

    Supervisor addSubworker(int supervisorId, int subworkerId) throws NoWorkerFoundException, WorkerIsNotSupervisorException;

    String getWorkerType(int workerId) throws NoWorkerFoundException;

    int calculateMonthSalary(int workerId) throws NoWorkerFoundException;

    int calculateMonthSalaryAllWorkers();

    String getTreeView(int workerId) throws NoWorkerFoundException;

}
