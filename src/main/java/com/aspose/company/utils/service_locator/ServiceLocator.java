package com.aspose.company.utils.service_locator;

import com.aspose.company.dao.EmployeeDao;
import com.aspose.company.dao.ManagerDao;
import com.aspose.company.dao.SalesDao;
import com.aspose.company.db.AppDB;
import com.aspose.company.exception.BeanTypeException;
import com.aspose.company.exception.NoBeanFoundException;
import com.aspose.company.service.CompanyServiceImpl;
import com.aspose.company.utils.calculation.SalaryCalculationServiceImpl;
import com.aspose.company.view.CompanyServiceView;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by serhii on 10/30/16.
 */
public final class ServiceLocator {

    private ServiceLocator() {
    }

    public static final Map<String, Object> context = new ConcurrentHashMap<>();

    static {
        context.put("salaryCalculatorService", new SalaryCalculationServiceImpl());
        context.put("appDb", new AppDB());
        context.put("employeeDao", new EmployeeDao());
        context.put("salesDao", new SalesDao());
        context.put("managerDao", new ManagerDao());
        context.put("companyService", new CompanyServiceImpl());
        context.put("companyServiceView", new CompanyServiceView());
    }

    public static <T> T getBean(String beanName, Class<T> tClass) throws NoBeanFoundException, BeanTypeException {
        Object bean = context.get(beanName);
        if (bean == null) {
            throw new NoBeanFoundException("no bean found with name " + beanName);
        }

        if (!tClass.isInstance(bean)) {
            throw new BeanTypeException(String.format("bean has other type(%s) than inputArgument (%s) ", bean.getClass(), tClass));
        }

        return tClass.cast(bean);
    }

    public static <T> T getBean(Class<T> tClass) {

        Optional bean = context.values().stream().filter(tClass::isInstance).findFirst();

        if (bean.isPresent()) {
            return tClass.cast(bean.get());
        } else {
            throw new NoBeanFoundException("No bean with a type of " + tClass.getName());
        }
    }

}
