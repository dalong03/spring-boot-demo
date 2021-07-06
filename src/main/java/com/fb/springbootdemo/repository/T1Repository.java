package com.fb.springbootdemo.repository;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fb.springbootdemo.entity.T1;

@Repository
public interface T1Repository extends BaseMapper<T1>{
	T1 findById(Integer id);
}
