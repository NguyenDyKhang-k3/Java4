package com.fpoly.service.impl;

import java.util.List;

import com.fpoly.dao.StatsDao;
import com.fpoly.dao.impl.StatsDaoImpl;
import com.fpoly.dto.VideoLikedInfo;
import com.fpoly.service.StatsService;

public class StatsServiceImpl implements StatsService {

	private StatsDao statsDao;

	public StatsServiceImpl() {
		statsDao = new StatsDaoImpl();
	}

	@Override
	public List<VideoLikedInfo> findVideoLikedInfo() {
		return statsDao.findVideoLikedInfo();
	}

}
