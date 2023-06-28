package com.example.maximtechnology.repositories;

import com.example.maximtechnology.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByDueDate(LocalDate today);

    List<Task> findByDueDateBetween(LocalDate startDay, LocalDate endDay);

    List<Task> findByDueDateAndCompleted(LocalDate today, boolean completed);

    List<Task> findByDueDateBetweenAndCompleted(LocalDate startDate, LocalDate endDate, boolean completed);
}
