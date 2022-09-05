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

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table
public class Compliance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long complianceId;
	private String rlType;
	private String details;

	@CreationTimestamp
	private Date createDate;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "departmentId")
	private Department department;

	private Long empCount;
	private Long stsCount;
	private String status;
}
