package com.example.maximtechnology.controllers;

import com.example.maximtechnology.dto.TaskDto;
import com.example.maximtechnology.models.Task;
import com.example.maximtechnology.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createTask(@RequestBody Task task) {
        taskService.createTask(task);
        return "The task was created";
    }

    @GetMapping("/today")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getTasksForToday(@RequestParam(name = "completed", required = false) Boolean completed) {
        if (completed == null)
            return taskService.getAllTasksForToday();

        return taskService.getTasksForTodayByCompleted(completed);
    }

    @GetMapping("/week")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getTasksForWeek(@RequestParam(name = "completed", required = false) Boolean completed) {
        if (completed == null)
            return taskService.getAllTasksForWeek();

        return taskService.getTasksForWeekByCompleted(completed);
    }

    @GetMapping("/month")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getTasksForMonth(@RequestParam(name = "completed", required = false) Boolean completed) {
        if (completed == null)
            return taskService.getAllTasksForMonth();

        return taskService.getTasksForMonthByCompleted(completed);
    }

    @PostMapping("/{id}/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TaskDto updateTaskById(@PathVariable("id") Long id, @RequestBody Task task) {
        task.setId(id);
        return taskService.updateTaskById(task);
    }

    @PostMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteTaskById(@PathVariable("id") Long id) {
        taskService.deleteTaskById(id);

        return "Task with id " + id + " was deleted";
    }

    @PostMapping("/{id}/completed")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TaskDto updateTaskStatus(@PathVariable("id") Long id, @RequestParam(name = "completed", required = false) Boolean completed){
        return taskService.updateTaskStatus(id, Objects.requireNonNullElse(completed, true));

    }
}
