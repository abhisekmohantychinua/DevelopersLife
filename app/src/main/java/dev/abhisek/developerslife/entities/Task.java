package dev.abhisek.developerslife.entities;

import dev.abhisek.developerslife.utils.AppUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {
    private int id;
    private String task;
    private LocalDateTime createdAt;
    private LocalDateTime lastEditedAt;
    private TaskType taskType;
    private Status status;

    /**
     * This method is used for converting entity to CSV
     */
    public String to_CSV() {

        String TASK_CSV = null;
        TASK_CSV = id + ","
                + task + ","
                + AppUtils.LocalDateTimeToString(createdAt) + ","
                + AppUtils.LocalDateTimeToString(lastEditedAt) + ","
                + taskType + ","
                + status + "\n";
        return TASK_CSV;
    }

    /**
     * This method converts entity to format string which will be displayed in console.
     */
    @Override
    public String toString() {
        return "\n___________________________________________________________________________________"
                + "\nTask : " + id
                + "\n" + task
                + "\nCreated : " + AppUtils.LocalDateTimeToString(createdAt)
                + "\nLast edited: " + AppUtils.LocalDateTimeToString(lastEditedAt)
                + "\n" + taskType + "\t status: " + status
                + "\n__________________________________________________________________________________";
    }
}
