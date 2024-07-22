package org.example.dto;

import org.example.model.Task;

public class DTOUtils {
    public static TaskDTO getDTO(Task event) {
        return null;
    }
    public static TaskDTO[] getDTO(Task[] tasks) {
        TaskDTO[] frDTO = new TaskDTO[tasks.length];
        for(int i=0; i<tasks.length; i++)
            frDTO[i] = getDTO(tasks[i]);
        return frDTO;
    }
}
