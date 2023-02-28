package com.neom.framework.repository;

import org.springframework.data.repository.CrudRepository;

import com.neom.framework.entity.Department;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

}
