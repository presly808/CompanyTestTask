package com.aspose.company.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by serhii on 10/29/16.
 */
public abstract class Supervisor extends Worker {

    private List<Worker> subWorkers = new ArrayList<>();

    public Supervisor(String name, int monthSalary, Date hireDate) {
        super(name, monthSalary, hireDate);
    }

    public List<Worker> getSubWorkers() {
        return subWorkers;
    }

    public void setSubWorkers(List<Worker> subWorkers) {
        this.subWorkers = subWorkers;
    }
}
