package com.fb.springbootdemo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "t2")
@JsonIgnoreProperties({"t1"})
public class T4 {

	@Id
	private Integer id;
	
	@ManyToOne(targetEntity = T3.class)
	@JoinColumn(name = "t1_id", referencedColumnName = "id")
	private T3 t1;
	
	private String name;

	public T4() {
	}

	public T4(Integer id, String name) {
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

	public T3 getT1() {
		return t1;
	}

	public void setT1(T3 t1) {
		this.t1 = t1;
	}


}
