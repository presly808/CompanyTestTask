## Business Logic ##

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

## Future Issues ##

## Recommendations ##

* Use ```com.aspose.company.utils.visualization.PrintUtils``` to use a tree view
