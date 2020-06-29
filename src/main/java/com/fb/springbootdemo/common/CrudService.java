package com.fb.springbootdemo.common;

import java.util.List;

import com.fb.springbootdemo.model.PageInfo;

public interface CrudService<E> {

	E find(Integer id);
	
	E find(E e);

	List<E> findList(E e);

	List<E> findPage(E e, E order, PageInfo pageInfo);
	
	E update(E e);
}
