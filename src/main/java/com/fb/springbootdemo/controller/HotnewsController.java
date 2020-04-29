package com.fb.springbootdemo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fb.springbootdemo.model.Hotnews;
import com.fb.springbootdemo.repository.HotnewsRepository;

@RestController
@RequestMapping("/hotnews")
public class HotnewsController {

	@Autowired
	private HotnewsRepository repository;

	@RequestMapping("")
	public Map<String, Object> findAll() {
		Map<String, Object> ret = new HashMap<String, Object>();
		Iterable<Hotnews> it = repository.findAll();
		if (it != null) {
			List<Hotnews> list = new ArrayList<>();
			if (it.iterator() != null) {
				Iterator<Hotnews> ite = it.iterator();
				while (ite.hasNext()) {
					list.add(ite.next());
				}
			}
			ret.put("list", list);
		}
		return ret;

	}

}
