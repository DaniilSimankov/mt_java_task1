package com.example.maximtechnology.services;

import com.example.maximtechnology.dto.TaskDto;
import com.example.maximtechnology.models.Task;

import java.util.List;

public interface TaskService {
    TaskDto createTask(Task task);

    List<TaskDto> getAllTasksForToday();

    List<TaskDto> getAllTasksForWeek();

    List<TaskDto> getAllTasksForMonth();

    List<TaskDto> getTasksForTodayByCompleted(boolean completed);

    List<TaskDto> getTasksForWeekByCompleted(boolean completed);

    List<TaskDto> getTasksForMonthByCompleted(boolean completed);

    TaskDto updateTaskById(Task task);

    void deleteTaskById(Long id);

    TaskDto updateTaskStatus(Long id, boolean isCompleted);
}
