package com.employee.management.system.api.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class StatusReport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long statusId;
	private String comments;
	private String details;
	
	@CreationTimestamp
	private Date createDate;
	
	private Long userId;
	private Long complianceId;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "departmentId")
	private Department department;
}
