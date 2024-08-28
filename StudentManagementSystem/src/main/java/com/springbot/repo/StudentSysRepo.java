package com.springbot.repo;

import org.springframework.data.repository.CrudRepository;

import com.springbot.entity.Students;

public interface StudentSysRepo extends CrudRepository<Students, Integer> {

}
