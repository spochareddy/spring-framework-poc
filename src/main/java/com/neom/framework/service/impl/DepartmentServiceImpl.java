package com.neom.framework.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neom.framework.entity.Department;
import com.neom.framework.repository.DepartmentRepository;
import com.neom.framework.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Department saveDepartment(Department department) {
		log.info("inside saveDepartment method :: " + department.toString());
		return departmentRepository.save(department);
	}

	@Override
	public List<Department> fetchDepartmentList() {
		
		List<Department> list =  (List<Department>) departmentRepository.findAll();
		log.info("inside fetchDepartmentList method :: size is :: " + list.size());
		return list;
	}

	@Override
	public Department updateDepartment(Department department, Long departmentId) {

		log.info("inside updateDepartment method :: departmentId ::" + departmentId);
		
		Department depDB = departmentRepository.findById(departmentId).get();

		if (Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName())) {
			depDB.setDepartmentName(department.getDepartmentName());
		}

		if (Objects.nonNull(department.getDepartmentAddress())
				&& !"".equalsIgnoreCase(department.getDepartmentAddress())) {
			depDB.setDepartmentAddress(department.getDepartmentAddress());
		}

		if (Objects.nonNull(department.getDepartmentCode()) && !"".equalsIgnoreCase(department.getDepartmentCode())) {
			depDB.setDepartmentCode(department.getDepartmentCode());
		}

		return departmentRepository.save(depDB);
	}

	@Override
	public void deleteDepartmentById(Long departmentId) {
		
		log.info("inside deleteDepartmentById method :: departmentId ::" + departmentId);
		
		departmentRepository.deleteById(departmentId);

	}

}
