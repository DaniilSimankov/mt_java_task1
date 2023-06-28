package com.example.maximtechnology.exceptions;

import lombok.NonNull;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(@NonNull Long id){
        super("Not found task with id " + id);
    }
}
