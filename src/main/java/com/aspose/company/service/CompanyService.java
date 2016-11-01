package com.aspose.company.service;

import com.aspose.company.exception.AppValidationException;
import com.aspose.company.exception.NoSupportedSubTypeException;
import com.aspose.company.exception.NoWorkerFoundException;
import com.aspose.company.exception.WorkerIsNotSupervisorException;
import com.aspose.company.model.Worker;

import java.util.Date;
import java.util.List;

/**
 * Created by serhii on 10/29/16.
 */
public interface CompanyService {

    Worker addWorker(Worker newWorker) throws NoSupportedSubTypeException;

    Worker getById(int id) throws NoWorkerFoundException;

    List<Worker> searchByName(String name);

    List<Worker> getAllWorkers();

    boolean addSubworker(int supervisorId, int subworkerId) throws NoWorkerFoundException, WorkerIsNotSupervisorException;

    String getWorkerType(int workerId) throws NoWorkerFoundException;

    int calculateMonthSalary(int workerId) throws NoWorkerFoundException;

    int calculateMonthSalaryAllWorkers();

}
