package com.aspose.company.db;

import com.aspose.company.model.Employee;
import com.aspose.company.model.Manager;
import com.aspose.company.model.Sales;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by serhii on 10/31/16.
 */
public class AppDB {

    public Map<Integer, Employee> employeeMap;
    public Map<Integer, Sales> salesMap;
    public Map<Integer, Manager> managerMap;

    public AppDB() {
        employeeMap = new ConcurrentHashMap<>();
        salesMap = new ConcurrentHashMap<>();
        managerMap = new ConcurrentHashMap<>();
    }


}
