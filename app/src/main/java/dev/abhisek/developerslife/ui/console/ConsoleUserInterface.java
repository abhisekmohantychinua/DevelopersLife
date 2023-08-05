package dev.abhisek.developerslife.ui.console;

import dev.abhisek.developerslife.storage.FileOperations;
import dev.abhisek.developerslife.utils.AppUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The CUI
 */
public class ConsoleUserInterface {

    /**
     * This constructor begins the CUI app
     *
     * @param PATH_TO_STORAGE the path to storage of data
     * @throws InterruptedException
     */
    public ConsoleUserInterface(String PATH_TO_STORAGE) throws InterruptedException {
        try {
            Scanner scanner = new Scanner(System.in);
            FileOperations fileOperations = new FileOperations(PATH_TO_STORAGE);
            System.out.println("Starting console user interface....");
            boolean status = true;
            while (status) {
                System.out.println("""
                        +-------------------------------------------------------------+              
                        |   1. update your work                                       |
                        |   2. read all your work history                             |            
                        |   3. edit your work                                         |
                        |   4. delete your work                                       |
                        |   5. export CSV                                             |
                        |   6. exit DevelopersLife                                    |    
                        +-------------------------------------------------------------+                
                        Choose an option from above: """);
                int option = scanner.nextInt();
                //  Special switch case
                switch (option) {
                    case 1 -> fileOperations.save(AppUtils.inputTask());
                    case 2 -> System.out.println(fileOperations.findAll());
                    case 3 -> {
                        System.out.print("Enter your task id: ");
                        int id = scanner.nextInt();
                        System.out.println(fileOperations
                                .editTask(
                                        id,
                                        AppUtils.inputTask()
                                )
                        );
                    }
                    case 4 -> {
                        System.out.print("Enter your task id: ");
                        int id = scanner.nextInt();
                        System.out.println(
                                fileOperations
                                        .deleteTask(id)
                        );
                    }
                    case 5 -> AppUtils.exportCSV();
                    case 6 -> status = false;
                    default -> System.out.println("provide valid input!");

                }
            }
            System.out.println("__________________________________________________________________");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input");
        }
    }
}
