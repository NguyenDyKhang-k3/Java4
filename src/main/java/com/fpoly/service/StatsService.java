package com.fpoly.service;

import java.util.List;

import com.fpoly.dto.VideoLikedInfo;

public interface StatsService {
	
	List<VideoLikedInfo> findVideoLikedInfo();
	
}
