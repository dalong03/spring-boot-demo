package com.fb.springbootdemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fb.springbootdemo.model.Hotnews;

@Repository
public interface HotnewsRepository extends CrudRepository<Hotnews, String>{

}
