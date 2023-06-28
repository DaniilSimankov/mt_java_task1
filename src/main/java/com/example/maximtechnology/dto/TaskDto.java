package com.example.maximtechnology.dto;

import com.example.maximtechnology.models.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {
    private String title;
    private String description;

    private LocalDate dueDate;
    private boolean completed;

    public static TaskDto from(Task task){
        return TaskDto.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .completed(task.isCompleted())
                .build();
    }

    public static List<TaskDto> from(List<Task> tasks){
        return tasks.stream().map(TaskDto::from).toList();
    }
}
