package com.employee.management.system.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.employee.management.system.api.dto.DepartmentDto;
import com.employee.management.system.api.entity.Department;
import com.employee.management.system.api.exceptions.ResourceNotFoundException;
import com.employee.management.system.api.repo.DepartmentRepository;
import com.employee.management.system.api.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private static final Logger LOGGER = LogManager.getLogger(DepartmentServiceImpl.class);

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DepartmentDto createDepartment(DepartmentDto departmentDto) {

		LOGGER.info("====>>>> Go to inside DepartmentServiceImpl createDepartment Method DepartmentDto ["
				+ departmentDto + "]");

		Department department = this.modelMapper.map(departmentDto, Department.class);

		Department createDepartment = this.departmentRepository.save(department);

		LOGGER.info("====>>>> Go to inside DepartmentServiceImpl createDepartment Method Department ["
				+ createDepartment + "]");

		return this.modelMapper.map(createDepartment, DepartmentDto.class);
	}

	@Override
	public List<DepartmentDto> getAllDepartmentDtos() {
		List<Department> departments = this.departmentRepository.findAll();
		List<DepartmentDto> departmentDtos = departments.stream()
				.map((department) -> this.modelMapper.map(department, DepartmentDto.class))
				.collect(Collectors.toList());

		LOGGER.info("====>>>> Go to inside DepartmentServiceImpl getAllDepartments Method Department List ["
				+ departmentDtos + "]");

		return departmentDtos;
	}

	@Override
	public DepartmentDto getDepartmentDto(Long departmentId) {

		LOGGER.info("====>>>> Go to inside DepartmentServiceImpl getSingleDepartment Method DepartmentId ["
				+ departmentId + "]");

		Department department = this.departmentRepository.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Department", "Id", departmentId));

		LOGGER.info("====>>>> Go to inside DepartmentServiceImpl getSingleDepartment Method Department[" + department
				+ "]");

		return this.modelMapper.map(department, DepartmentDto.class);
	}

	@Override
	public void deleteDepartment(Long departmentId) {
		Department department = this.departmentRepository.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Department", "Id", departmentId));

		LOGGER.info(
				"====>>>> Go to inside DepartmentServiceImpl deleteDepartment Method Department [" + department + "]");

		this.departmentRepository.delete(department);
	}

	@Override
	public DepartmentDto updateDepartment(Long departmentId, DepartmentDto departmentDto) {

		LOGGER.info("====>>>> Go to inside DepartmentServiceImpl updateDepartment Method DepartmentDto ["
				+ departmentDto + "] DepartmentId [" + departmentId + "]");

		Department department = this.departmentRepository.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Department", "Id", departmentId));

		department.setName(departmentDto.getName());

		Department updatedDepartment = this.departmentRepository.save(department);

		LOGGER.info("====>>>> Go to inside DepartmentServiceImpl updateDepartment Method Department ["
				+ updatedDepartment + "]");

		return this.modelMapper.map(updatedDepartment, DepartmentDto.class);
	}

	@Override
	public List<DepartmentDto> getAllDepartmentByPagination(int pageNo, int pageSize) {

		LOGGER.info("====>>>> Go to inside DepartmentServiceImpl getAllDepartmentByPagination Method PAGE_NUMBER ["
				+ pageNo + "] PAGE_SIZE [" + pageSize + "]");

		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Department> pagedResult = this.departmentRepository.findAll(paging);
		List<Department> departments = pagedResult.toList();
		List<DepartmentDto> departmentDtos = departments.stream()
				.map((department) -> this.modelMapper.map(department, DepartmentDto.class)).collect(Collectors.toList());

		LOGGER.info("====>>>> Go to inside DepartmentServiceImpl getAllDepartmentByPagination Method Employee List ["
				+ departmentDtos + "]");

		return departmentDtos;
	}

	@Override
	public Long countDepartment() {
		
		Long totalDepartment = this.departmentRepository.count();
		
		LOGGER.info("====>>>> Go to inside  DepartmentServiceImpl countDepartments Method Total Department ["
				+ totalDepartment + "]");
		return totalDepartment;
	}

}
