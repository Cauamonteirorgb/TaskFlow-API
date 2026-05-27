package com.caua.joao.taskflowapi.repository;

import com.caua.joao.taskflowapi.entity.Status;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository<Status, Long> {
}