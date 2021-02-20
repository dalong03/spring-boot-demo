package com.fb.springbootdemo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "t2")
@JsonIgnoreProperties({"t1"})
public class T2 {

	@Id
	private Integer id;
	
	@ManyToOne(targetEntity = T1.class)
	@JoinColumn(name = "t1_id", referencedColumnName = "id")
	private T1 t1;
	
	private String name;

	public T2() {
	}

	public T2(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "T2 [id=" + id + ", t1=" + t1 + ", name=" + name + "]";
	}

	public T1 getT1() {
		return t1;
	}

	public void setT1(T1 t1) {
		this.t1 = t1;
	}


}
