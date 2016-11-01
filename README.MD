## Business Logic ##

#### Main Service ####
```java
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
```

## How to run! ##

1. install maven
2. ```git clone https://github.com/presly808/AsposeTask.git```
3. ```cd ${PROJECT_DIR}```
4. build project ```mvn build```
5. ```cd target```
6. run console app ```java -jar CompanyAsposeTask-1.0-jar-with-dependencies.jar ```

You can also run tests only ```mvn test```

## Project Architecture ##

#### Main App Components ####
![alt text](https://raw.githubusercontent.com/presly808/AsposeTask/master/resources/basic_diagram.png "main diagram")

```ServiceLocator``` holds all singletons as dao, services and others. It takes all objects creation in one place

#### Class Diagram ####
![alt text](https://raw.githubusercontent.com/presly808/AsposeTask/master/resources/diagram.png "Class Diagram")

#### Class Diagrams of the model package ####
![alt text](https://raw.githubusercontent.com/presly808/AsposeTask/master/resources/model.png "model diagram")

#### Important class for calculations of year benefit and subworkers hierarchy ####
```java
    public interface SalaryCalculationService {

    int calculateYearBenefit(int fixedSalary, double yearBenefitPercent, int fullYears, double limitOfYearBenefitPercent);

    /**
     * calculate benefits with level limitaion
     *
     * @param level limit a calculation level, if -1 we consider all hierarchy
     * */
    int calculateTeamBenefitByLevel(List<Worker> root, int level);
}
```

## Future Issues ##


## Recommendations ##

* Use ```com.aspose.company.utils.visualization.PrintUtils``` to use a tree view
* Change benefit percents via ```app.properties``` which is located in the resources
* Start point of the program is com.aspose.company.Run
