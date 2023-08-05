package dev.abhisek.developerslife.storage;

import dev.abhisek.developerslife.entities.Status;
import dev.abhisek.developerslife.entities.Task;
import dev.abhisek.developerslife.entities.TaskType;
import dev.abhisek.developerslife.utils.AppUtils;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * All the operations related to storage
 */
public class FileOperations {

    /**
     * Path to file which is treated as a database
     */
    private final String PATH_TO_FILE;

    /**
     * Sets the file name and creates file if exists
     * @param PATH_TO_STORAGE
     */
    public FileOperations(String PATH_TO_STORAGE) {
        String FILE_NAME = "storage.txt";
        this.PATH_TO_FILE = PATH_TO_STORAGE + File.separator + FILE_NAME;
        AppUtils.createFileIfNotExists(PATH_TO_FILE);
    }

    /**
     * This method saves the task in FileSystem
     *
     * @param task
     * @return saved task
     */
    public Task save(Task task) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_TO_FILE, true))) {
            writer.write(task.to_CSV());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return task;
    }

    /**
     * This method finds all the task as a list from file system
     *
     * @return list of tasks
     */
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_TO_FILE))) {
            updateTasks(tasks, bufferedReader);
            return tasks;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method finds an optional task from FileSystem by its id.
     * If the id is not present, it will print not found in the console.
     *
     * @param id
     * @return optional task by id
     */
    public Optional<Task> findTaskById(int id) {
        List<Task> tasks = findAll();
        return tasks != null ?
                tasks
                        .stream()
                        .filter(task -> task.getId() == id)
                        .findFirst()
                : Optional.empty();
    }

    /**
     * This method deletes the task with matching id
     *
     * @param id
     * @return the deleted task
     */
    public Task deleteTask(int id) {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_TO_FILE))) {
            updateTasks(tasks, bufferedReader);
            bufferedReader.close();
            Optional<Task> optionalTask = tasks
                    .stream()
                    .filter(task -> task.getId() == id)
                    .findFirst();
            if (optionalTask.isEmpty()) {
                System.out.println("No task found with id " + id);
                return null;
            }
            StringBuilder CSV_DATASET = new StringBuilder();
            tasks = tasks
                    .stream()
                    .filter(task -> task.getId() != id)
                    .toList();
            for (Task task :
                    tasks) {
                CSV_DATASET.append(task.to_CSV());
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_TO_FILE));
            writer.write(String.valueOf(CSV_DATASET));
            writer.close();
            return optionalTask.get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method finds a particular task by id, updates it and agains saves it
     *
     * @param id
     * @param taskToUpdate
     * @return updated task
     */
    public Task editTask(int id, Task taskToUpdate) {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_TO_FILE))) {
            updateTasks(tasks, bufferedReader);
            bufferedReader.close();
            Optional<Task> optionalTask = tasks
                    .stream()
                    .filter(task -> task.getId() == id)
                    .findFirst();
            if (optionalTask.isEmpty()) {
                System.out.println("No task found with id " + id);
                return null;
            }
            StringBuilder CSV_DATASET = new StringBuilder();
            tasks = tasks
                    .stream()
                    .peek(task -> {
                        if (task.getId() == id) {
                            task.setTask(taskToUpdate.getTask());
                            task.setLastEditedAt(LocalDateTime.now());
                        }
                    }).toList();
            for (Task task :
                    tasks) {
                CSV_DATASET.append(task.to_CSV());
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_TO_FILE));
            writer.write(String.valueOf(CSV_DATASET));
            writer.close();
            return optionalTask.get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method reads lines from FileSystem, converts it to entity.
     *
     * @param tasks
     * @param bufferedReader
     * @throws IOException
     */
    private void updateTasks(List<Task> tasks, BufferedReader bufferedReader) throws IOException {
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(",");
            Task task = Task.builder()
                    .id(Integer.parseInt(data[0]))
                    .task(data[1])
                    .createdAt(AppUtils.StringToLocalDateTime(data[2]))
                    .lastEditedAt(AppUtils.StringToLocalDateTime(data[3]))
                    .taskType(Enum.valueOf(TaskType.class, data[4]))
                    .status(Enum.valueOf(Status.class, data[5]))
                    .build();
            tasks.add(task);
        }
    }
}
