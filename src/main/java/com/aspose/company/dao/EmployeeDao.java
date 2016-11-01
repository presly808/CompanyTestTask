package com.aspose.company.dao;

import com.aspose.company.db.AppDB;
import com.aspose.company.model.Employee;
import com.aspose.company.utils.generator.DataGenerationUtils;
import com.aspose.company.utils.service_locator.ServiceLocator;

import java.util.Collection;

/**
 * Created by serhii on 10/31/16.
 */
public class EmployeeDao implements GeneralDao<Employee,Integer> {

    private AppDB appDB = ServiceLocator.getBean(AppDB.class);

    @Override
    public Employee getById(Integer id) {
        return appDB.employeeMap.get(id);
    }

    @Override
    public Employee create(Employee el) {
        int id = DataGenerationUtils.generateId();
        el.setId(id);
        appDB.employeeMap.put(id,el);
        return el;
    }

    @Override
    public Employee delete(Integer id) {
        return appDB.employeeMap.remove(id);
    }

    @Override
    public Collection<Employee> getAll() {
        return appDB.employeeMap.values();
    }
}
