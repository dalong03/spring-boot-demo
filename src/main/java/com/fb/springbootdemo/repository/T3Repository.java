package com.fb.springbootdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fb.springbootdemo.entity.T3;

@Repository
public interface T3Repository extends CrudRepository<T3, Integer>{
	@Query(nativeQuery = true, value = "select t1.id, t1.name, t2.id as t2_id, t2.name as t2_name from t1 left join t2 on t1.id = t2.t1_id where t1.version = :version")
	List<Object[]> findByVersion(@Param("version") Integer version);
}
