package com.fb.springbootdemo.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value = "T1")
@Data
public class T1 implements Serializable {

	private static final long serialVersionUID = 4707070736316178952L;
	@TableId(type = IdType.AUTO)
	private Long id;
	private String name;
	private Date birthday;
	private Integer version;
}
