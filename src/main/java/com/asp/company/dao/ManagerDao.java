package com.asp.company.dao;

import com.asp.company.db.AppDB;
import com.asp.company.model.Manager;
import com.asp.company.utils.generator.DataGenerationUtils;
import com.asp.company.utils.service_locator.ServiceLocator;

import java.util.Collection;

/**
 * Created by serhii on 10/31/16.
 */
public class ManagerDao implements GeneralDao<Manager, Integer> {

    private AppDB appDB = ServiceLocator.getBean(AppDB.class);

    @Override
    public Manager getById(Integer id) {
        return appDB.managerMap.get(id);
    }

    @Override
    public Manager create(Manager el) {
        int id = DataGenerationUtils.generateId();
        el.setId(id);
        appDB.managerMap.put(id, el);
        return el;
    }

    @Override
    public Manager delete(Integer id) {
        return appDB.managerMap.remove(id);
    }

    @Override
    public Collection<Manager> getAll() {
        return appDB.managerMap.values();
    }
}
