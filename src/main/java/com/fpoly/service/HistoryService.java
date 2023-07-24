package com.fpoly.service;

import java.util.List;

import com.fpoly.entity.History;
import com.fpoly.entity.User;
import com.fpoly.entity.Video;

public interface HistoryService {

	List<History> findByUser(String username);

	List<History> findByUserAndIsLiked(String username);

	History findByUserIdAndVideoId(Integer userId, Integer videoId);

	History create(User user, Video video);

	Boolean updateLikeOrUnLike(User userId, String videoHref);


}
