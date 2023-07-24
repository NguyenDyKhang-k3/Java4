package com.fpoly.dao;

import java.util.List;

import com.fpoly.entity.Video;

public interface VideoDao {

	Video findById(Integer id);
	Video findByHref(String href);
	List<Video> findAll();
	List<Video> findAll(int pageNumber, int pageSize);
	Video create(Video entity);
	Video update(Video entity);
	Video delete(Video entity);
}
