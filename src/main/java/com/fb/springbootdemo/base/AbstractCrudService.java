package com.fb.springbootdemo.base;

import java.util.List;

import com.fb.springbootdemo.model.PageInfo;

public abstract class AbstractCrudService<E> implements CrudService<E>{

	@Override
	public E find(Integer id) {
		return null;
	}

	@Override
	public E find(E e) {
		return null;
	}

	@Override
	public List<E> findList(E e) {
		return null;
	}

	@Override
	public List<E> findPage(E e, E order, PageInfo pageInfo) {
		return null;
	}

}
