package dev.abhisek.developerslife.utils;

import dev.abhisek.developerslife.entities.Status;
import dev.abhisek.developerslife.entities.Task;
import dev.abhisek.developerslife.entities.TaskType;
import dev.abhisek.developerslife.storage.FileOperations;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * Utility Class
 */
public class AppUtils {
    /**
     * Formatting pattern of LocalDateTime Object
     */
    public static String PATTERN = "HH:mm:ss    dd-MM-yyyy";


    /**
     * DateTimeFormatter object with above pattern
     */
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);

    /**
     * The Scanner class object for user input in CUI
     */
    public static Scanner scanner = new Scanner(System.in);

    /**
     * This gets the home directory of an OS (platform independent)
     */
    public static String USER_HOME = System.getProperty("user.home");

    /**
     * Path to file that handles AUTO INCREMENT
     */
    public static String SEQ_FILE_PATH = USER_HOME + File.separator + "DevelopersLife" + File.separator + "seq.txt";

    /**
     * Path to the folder where data are stored
     */
    public static String PATH_TO_STORAGE = USER_HOME + File.separator + "DevelopersLife";

    /**
     * This method converts LocalDateTime Object to formatted string
     *
     * @param localDateTime
     * @return formatted string
     */
    public static String LocalDateTimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(formatter);
    }

    /**
     * This method converts formatted string to LocalDateTime Object
     *
     * @param localDateTime formatted string
     * @return parsed LocalDateTime
     */
    public static LocalDateTime StringToLocalDateTime(String localDateTime) {
        return LocalDateTime.parse(localDateTime, formatter);
    }

    /**
     * This method creates the file at the path if not existing
     *
     * @param filePath path to file
     */
    public static void createFileIfNotExists(String filePath) {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Inputs a task from console
     *
     * @return task from input
     */
    public static Task inputTask() {
        System.out.print("Enter task to add: ");
        String task = scanner.nextLine();
        return Task.builder()
                .id(autoIncrement())
                .task(task)
                .createdAt(LocalDateTime.now())
                .lastEditedAt(LocalDateTime.now())
                .taskType(TaskType.TASK)
                .status(Status.UNCHECKED)
                .build();
    }

    /**
     * This method is the implementation of AUTO INCREMENT as like Database
     *
     * @return auto incremented value
     */
    public static int autoIncrement() {
        createFileIfNotExists(SEQ_FILE_PATH);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(SEQ_FILE_PATH));
            String seq = reader.readLine();
            reader.close();
            int count = seq != null ? Integer.parseInt(seq) : 0;
            count++;
            BufferedWriter writer = new BufferedWriter(new FileWriter(SEQ_FILE_PATH));
            writer.write(Integer.toString(count));
            writer.close();
            return count;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * This method converts the whole FileSystem i.e .txt to .csv
     */
    public static void exportCSV() {

        try {
            FileOperations fileOperations = new FileOperations(PATH_TO_STORAGE);
            List<Task> tasks = fileOperations.findAll();
            JFrame parent = new JFrame();
            parent.setAlwaysOnTop(true);
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fileChooser.setDialogTitle("Select where to save");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            System.out.println("minimize the window and select the folder path.");
            int returnValue = fileChooser.showDialog(parent, "Select");
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                String selectedPath = fileChooser.getSelectedFile().getAbsolutePath();
                createFileIfNotExists(selectedPath + File.separator + "tasks.csv");
                System.out.println("PATH_TO_FILE: " + selectedPath + File.separator + "tasks.csv");
                BufferedWriter writer = new BufferedWriter(new FileWriter(selectedPath + File.separator + "tasks.csv", true));
                writer.write("id,task,created at,last edited at,tasktype,status\n");
                for (Task task : tasks) {
                    writer.write(task.to_CSV());
                }
                writer.close();
                System.out.println("Done");
            } else {
                System.out.println("Cancelled\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
