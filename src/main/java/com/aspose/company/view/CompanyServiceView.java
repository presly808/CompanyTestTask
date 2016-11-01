package com.aspose.company.view;

import com.aspose.company.exception.NoSupportedSubTypeException;
import com.aspose.company.exception.NoWorkerFoundException;
import com.aspose.company.exception.WorkerIsNotSupervisorException;
import com.aspose.company.model.*;
import com.aspose.company.service.CompanyService;
import com.aspose.company.utils.service_locator.ServiceLocator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by serhii on 11/1/16.
 */
public class CompanyServiceView {

    private CompanyService companyService = ServiceLocator.getBean(CompanyService.class);

    private Scanner scanner = new Scanner(System.in);

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");

    public void start(){

        int choice = 0;
        while (choice != 0){
            showMainMenu();
            System.out.println("do choice");
            choice = inputNumber();

            switch (choice){
                case 1 :
                    showAllWorkers();
                    break;
                case 2:
                    showAddWorkerMenu();
                    break;
                case 3:
                    showAddSubworkerMenu();
                    break;
                case 4:
                    showGetWorkerByIdMenu();
                    break;
                case 5:
                    showFindWorkersByNameMenu();
                    break;
                case 6:
                    showGetWorkerTypeMenu();
                    break;
                case 7:
                    showCalculateWorkerSalaryMenu();
                    break;
                case 8:
                    showCalculateWorkersSalaryMenu();
                    break;
                case 0:
                    System.out.println("Good bye");
                    break;
                default:
                    System.out.println("Do choice between 0-8");
            }
        }


    }

    private void showCalculateWorkersSalaryMenu() {
        System.out.println("Workers amount salary");
        System.out.println(companyService.calculateMonthSalaryAllWorkers());
    }

    private void showCalculateWorkerSalaryMenu() {
        System.out.println("Calculate worker month salary");
        System.out.println("Input worker id");
        int workerId = inputNumber();
        try {
            int monthCalculatedSalary = companyService.calculateMonthSalary(workerId);
            System.out.println(String.format("Worker with id=%s has %d", workerId, monthCalculatedSalary));
        } catch (NoWorkerFoundException e) {
            e.printStackTrace();
        }
    }

    private void showGetWorkerTypeMenu() {
        System.out.println("Get type of worker");
        System.out.println("Input worker id");
        int workerId = inputNumber();
        try {
            String type = companyService.getWorkerType(workerId);
            System.out.println("worker type = " + type);
        } catch (NoWorkerFoundException e) {
            e.printStackTrace();
        }
    }

    private void showFindWorkersByNameMenu() {
        System.out.println("Find workers by name");
        System.out.println("Input search keyword");
        String keyWord = inputStr();
        List<Worker> workers = companyService.searchByName(keyWord);
        workers.stream().forEach(System.out::println);

    }

    private void showGetWorkerByIdMenu() {
        System.out.println("Get worker info");
        System.out.println("Input worker id");
        int id = inputNumber();
        try {
            Worker worker = companyService.getById(id);
            System.out.println(worker);
        } catch (NoWorkerFoundException e) {
            e.printStackTrace();
        }
    }

    private void showMainMenu() {
        System.out.println("MAIN MENU");
        System.out.println("1. Show workers");
        System.out.println("2. Add worker");
        System.out.println("3. Add subworker");
        System.out.println("4. Get worker info");
        System.out.println("5. Find workers by name");
        System.out.println("6. Get type of worker");
        System.out.println("7. Calculate worker month salary");
        System.out.println("8. Calculate a month salary of all workers");
        System.out.println();

    }

    private void showAddSubworkerMenu() {
        System.out.println("ADD SUBWORKER MENU");
        System.out.println("Input supervisor id");
        int supervisorId = inputNumber();
        System.out.println("Input subworker id");
        int subworker = inputNumber();

        try {
            companyService.addSubworker(supervisorId, subworker);
            System.out.println("Successfully added");
        } catch (NoWorkerFoundException e) {
            e.printStackTrace();
        } catch (WorkerIsNotSupervisorException e) {
            e.printStackTrace();
        }

    }

    private void showAllWorkers() {
        List<Worker> workers = companyService.getAllWorkers();
        System.out.println("\nALL WORKERS");
        workers.stream().forEach(System.out::println);

    }

    private int inputNumber() {
        return Integer.parseInt(scanner.nextLine());
    }

    private void showAddWorkerMenu() {
        System.out.println("ADD WORKER MENU");

        System.out.println("Input name");
        String name = scanner.nextLine();
        System.out.println("Input salary");
        int salary = inputNumber();
        System.out.println("Input hire date. Format yyyy-mm-dd");
        Date hireDate = inputDate();

        System.out.println("Choose type");
        System.out.println("1. Employee");
        System.out.println("2. Sales");
        System.out.println("3. Manager");

        int choice = inputNumber();
        Worker worker = null;

        switch (choice){
            case 1:
                worker = new Employee(name,salary,hireDate);
                break;
            case 2:
                worker = new Sales(name,salary,hireDate);
                break;
            case 3:
                worker = new Manager(name,salary,hireDate);
                break;
            default:
                worker = new Employee(name,salary,hireDate);
                break;
        }

        try {
            Worker worker1 = companyService.addWorker(worker);
            System.out.println("worker was added, id = " + worker1.getId());
        } catch (NoSupportedSubTypeException e) {
            e.printStackTrace();
        }


    }

    private Date inputDate() {
        String dateStr = scanner.nextLine();
        try {
            return simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    private String inputStr() {
        return scanner.nextLine();
    }


}
