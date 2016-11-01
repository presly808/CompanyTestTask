package com.aspose.company.dao;

import com.aspose.company.db.AppDB;
import com.aspose.company.model.Sales;
import com.aspose.company.utils.generator.DataGenerationUtils;
import com.aspose.company.utils.service_locator.ServiceLocator;

import java.util.Collection;

/**
 * Created by serhii on 10/31/16.
 */
public class SalesDao implements GeneralDao<Sales,Integer> {

    private AppDB appDB = ServiceLocator.getBean(AppDB.class);

    @Override
    public Sales getById(Integer id) {
        return appDB.salesMap.get(id);
    }

    @Override
    public Sales create(Sales el) {
        int id = DataGenerationUtils.generateId();
        el.setId(id);
        appDB.salesMap.put(id, el);
        return el;
    }

    @Override
    public Sales delete(Integer id) {
        return appDB.salesMap.remove(id);
    }

    @Override
    public Collection<Sales> getAll() {
        return appDB.salesMap.values();
    }
}
