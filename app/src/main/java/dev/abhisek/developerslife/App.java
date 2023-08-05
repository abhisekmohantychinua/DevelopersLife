package dev.abhisek.developerslife;

import dev.abhisek.developerslife.ui.console.ConsoleUserInterface;

import java.io.File;

public class App {
    /**
     * This method initializes the app
     *
     * @throws InterruptedException
     */
    public static void initializeApp() throws InterruptedException {

        String userHome = System.getProperty("user.home");
        String PATH_TO_STORAGE = userHome + File.separator + "DevelopersLife";  // Default file storage path
        boolean IS_PATH_VERIFIED = false;
        File folder = new File(PATH_TO_STORAGE);

        System.out.println("Initializing DevelopersLife ....");
        Thread.sleep(5000);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Creating storage....");
            boolean created = folder.mkdir();
            if (!created) {
                System.out.println("Some error occurred!");
                return;
            }
            IS_PATH_VERIFIED = true;
            System.out.println("Storage created successfully....");
        } else {
            IS_PATH_VERIFIED = true;
            System.out.println(PATH_TO_STORAGE);
        }
        System.out.println("Starting DevelopersLife....");
        Thread.sleep(3000);
        new ConsoleUserInterface(PATH_TO_STORAGE);
    }

    public static void main(String[] args) throws InterruptedException {
        // Initializing the app
        initializeApp();
    }


}

