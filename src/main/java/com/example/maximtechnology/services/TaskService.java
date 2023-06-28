package com.example.maximtechnology.services;

import com.example.maximtechnology.dto.TaskDto;
import com.example.maximtechnology.models.Task;

import java.util.List;

/**
 * The TaskService interface provides methods for managing tasks.
 */
public interface TaskService {
    /**
     * Creates a new task.
     *
     * @param task The task to create.
     * @return The created task DTO.
     */
    TaskDto createTask(Task task);

    /**
     * Retrieves all tasks for today.
     *
     * @return A list of task DTOs for today.
     */
    List<TaskDto> getAllTasksForToday();

    /**
     * Retrieves all tasks for the current week.
     *
     * @return A list of task DTOs for the week.
     */
    List<TaskDto> getAllTasksForWeek();

    /**
     * Retrieves all tasks for the current month.
     *
     * @return A list of task DTOs for the month.
     */
    List<TaskDto> getAllTasksForMonth();

    /**
     * Retrieves tasks for today based on the completion status.
     *
     * @param completed The completion status to filter tasks.
     * @return A list of task DTOs for today filtered by completion status.
     */
    List<TaskDto> getTasksForTodayByCompleted(boolean completed);

    /**
     * Retrieves tasks for the current week based on the completion status.
     *
     * @param completed The completion status to filter tasks.
     * @return A list of task DTOs for the week filtered by completion status.
     */
    List<TaskDto> getTasksForWeekByCompleted(boolean completed);

    /**
     * Retrieves tasks for the current month based on the completion status.
     *
     * @param completed The completion status to filter tasks.
     * @return A list of task DTOs for the month filtered by completion status.
     */
    List<TaskDto> getTasksForMonthByCompleted(boolean completed);

    /**
     * Updates a task by its ID.
     *
     * @param task The updated task.
     * @return The updated task DTO.
     */
    TaskDto updateTaskById(Task task);

    /**
     * Deletes a task by its ID.
     *
     * @param id The ID of the task to delete.
     */
    void deleteTaskById(Long id);

    /**
     * Updates the completion status of a task.
     *
     * @param id          The ID of the task to update.
     * @param isCompleted The new completion status of the task.
     * @return The updated task DTO.
     */
    TaskDto updateTaskStatus(Long id, boolean isCompleted);
}
