package com.aspose.company.utils.visualization;

import com.aspose.company.exception.NoSupportedSubTypeException;
import com.aspose.company.model.*;
import com.aspose.company.utils.generator.DataGenerationUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by serhii on 11/1/16.
 */
public class PrintUtils {

    /**
     *
     *
     * @param level limit for tree view, if you pass <code>-1</code> then you will get all levels
     *
     * @return string representation of hierarchy
     * */
    public static String getTreeView(final List<Worker> workerList, int level) {
        return getTreeView(workerList, level, "");
    }

    private static String getTreeView(final List<Worker> workerList, int level, String tab) {

        if(level == 0 || (workerList == null || workerList.isEmpty())){
            return "";
        }

        StringBuilder levelView = new StringBuilder();

        for (Worker worker : workerList) {
            // todo show a data by input filter
            String workerInfo = String.format("type:%s,name:%s,salary:%d,calculatedSalary:%d",
                    worker.getClass().getSimpleName(),
                    worker.getName(),
                    worker.getMonthSalary(),
                    worker.calculateSalary());

            levelView.append(tab + workerInfo + "\n");

            if(worker instanceof Supervisor){
                levelView.append(getTreeView(((Supervisor) worker).getSubWorkers(), level - 1, tab + "\t"));
            }
        }

        return levelView.toString();
    }

}
