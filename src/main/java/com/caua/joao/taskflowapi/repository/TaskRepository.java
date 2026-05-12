package com.caua.joao.taskflowapi.repository;

import com.caua.joao.taskflowapi.entity.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}