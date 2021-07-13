package com.fb.springbootdemo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name = "t1")
@Data
public class T3 {

//	@NotBlank(message = "id为空")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank(message = "name为空")
	private String name;
	private Date birthday;
	private Integer version;

//	@OneToMany(targetEntity = T2.class, cascade = { CascadeType.PERSIST })
//	@JoinColumn(name = "t1_id", referencedColumnName = "id")
//	private List<T2> contacts;
}
