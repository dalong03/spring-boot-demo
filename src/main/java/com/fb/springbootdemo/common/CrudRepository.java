package com.fb.springbootdemo.common;

import java.util.List;

import com.fb.springbootdemo.model.PageInfo;

public interface CrudRepository<E> {

	E find(Integer id);
	
	E find(E e);

	List<E> findList(E e);

	/**
	 * 分页查询
	 * @param e 查询参数
	 * @param order 排序
	 * @param pageInfo 分页
	 * @return
	 */
	List<E> findPage(E e, E order, PageInfo pageInfo);
	
	int update(E e);
}
