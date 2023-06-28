package com.example.maximtechnology.controllers;

import com.example.maximtechnology.dto.TaskDto;
import com.example.maximtechnology.models.Task;
import com.example.maximtechnology.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * The TaskController class is responsible for handling HTTP requests related to tasks.
 */
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    /**
     * Creates a new task.
     *
     * @param task The task to create.
     * @return A success message.
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createTask(@RequestBody Task task) {
        taskService.createTask(task);
        return "The task was created";
    }

    /**
     * Retrieves tasks for today.
     *
     * @param completed The completion status to filter tasks (optional).
     * @return A list of task DTOs for today.
     */
    @GetMapping("/today")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getTasksForToday(@RequestParam(name = "completed", required = false) Boolean completed) {
        if (completed == null)
            return taskService.getAllTasksForToday();

        return taskService.getTasksForTodayByCompleted(completed);
    }

    /**
     * Retrieves tasks for the current week.
     *
     * @param completed The completion status to filter tasks (optional).
     * @return A list of task DTOs for the week.
     */
    @GetMapping("/week")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getTasksForWeek(@RequestParam(name = "completed", required = false) Boolean completed) {
        if (completed == null)
            return taskService.getAllTasksForWeek();

        return taskService.getTasksForWeekByCompleted(completed);
    }

    /**
     * Retrieves tasks for the current month.
     *
     * @param completed The completion status to filter tasks (optional).
     * @return A list of task DTOs for the month.
     */
    @GetMapping("/month")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getTasksForMonth(@RequestParam(name = "completed", required = false) Boolean completed) {
        if (completed == null)
            return taskService.getAllTasksForMonth();

        return taskService.getTasksForMonthByCompleted(completed);
    }

    /**
     * Updates a task by its ID.
     *
     * @param id   The ID of the task to update.
     * @param task The updated task.
     * @return The updated task DTO.
     */
    @PostMapping("/{id}/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TaskDto updateTaskById(@PathVariable("id") Long id, @RequestBody Task task) {
        task.setId(id);
        return taskService.updateTaskById(task);
    }

    /**
     * Deletes a task by its ID.
     *
     * @param id The ID of the task to delete.
     * @return A success message.
     */
    @PostMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteTaskById(@PathVariable("id") Long id) {
        taskService.deleteTaskById(id);

        return "Task with id " + id + " was deleted";
    }

    /**
     * Updates the completion status of a task.
     *
     * @param id        The ID of the task to update.
     * @param completed The new completion status of the task (optional, default is true).
     * @return The updated task DTO.
     */
    @PostMapping("/{id}/completed")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TaskDto updateTaskStatus(@PathVariable("id") Long id, @RequestParam(name = "completed", required = false) Boolean completed){
        return taskService.updateTaskStatus(id, Objects.requireNonNullElse(completed, true));

    }
}
