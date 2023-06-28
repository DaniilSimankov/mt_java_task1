package com.example.maximtechnology.services;

import com.example.maximtechnology.dto.TaskDto;
import com.example.maximtechnology.exceptions.TaskNotFoundException;
import com.example.maximtechnology.models.Task;
import com.example.maximtechnology.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public TaskDto createTask(Task task) {
        return TaskDto.from(taskRepository.save(task));
    }

    @Override
    public List<TaskDto> getAllTasksForToday() {
        LocalDate today = LocalDate.now();

        return TaskDto.from(taskRepository.findByDueDate(today));
    }

    @Override
    public List<TaskDto> getAllTasksForWeek() {
        LocalDate startDay = LocalDate.now();
        LocalDate endDay = startDay.plusWeeks(1);

        return TaskDto.from(taskRepository.findByDueDateBetween(startDay, endDay));
    }

    @Override
    public List<TaskDto> getAllTasksForMonth() {
        LocalDate startDay = LocalDate.now();
        LocalDate endDay = startDay.plusMonths(1);

        return TaskDto.from(taskRepository.findByDueDateBetween(startDay, endDay));
    }

    @Override
    public List<TaskDto> getTasksForTodayByCompleted(boolean completed) {
        LocalDate today = LocalDate.now();

        return TaskDto.from(taskRepository.findByDueDateAndCompleted(today, completed));
    }

    @Override
    public List<TaskDto> getTasksForWeekByCompleted(boolean completed) {
        LocalDate startDay = LocalDate.now();
        LocalDate endDay = startDay.plusWeeks(1);

        return TaskDto.from(taskRepository.findByDueDateBetweenAndCompleted(startDay, endDay, completed));
    }

    @Override
    public List<TaskDto> getTasksForMonthByCompleted(boolean completed) {
        LocalDate startDay = LocalDate.now();
        LocalDate endDay = startDay.plusMonths(1);

        return TaskDto.from(taskRepository.findByDueDateBetweenAndCompleted(startDay, endDay, completed));

    }

    @Override
    public TaskDto updateTaskById(Task changedTask) {
        Task task = taskRepository.findById(changedTask.getId()).orElseThrow(() -> {
            throw new TaskNotFoundException(changedTask.getId());
        });

        task.setTitle(changedTask.getTitle());
        task.setDescription(changedTask.getDescription());
        task.setDueDate(changedTask.getDueDate());
        task.setCompleted(changedTask.isCompleted());

        return TaskDto.from(taskRepository.save(task));
    }

    @Override
    public void deleteTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> {
            throw new TaskNotFoundException(id);
        });

        taskRepository.delete(task);
    }

    @Override
    public TaskDto updateTaskStatus(Long id, boolean isCompleted) {
        Task task = taskRepository.findById(id).orElseThrow(() -> {
            throw new TaskNotFoundException(id);
        });

        task.setCompleted(isCompleted);

        return TaskDto.from(taskRepository.save(task));
    }
}
