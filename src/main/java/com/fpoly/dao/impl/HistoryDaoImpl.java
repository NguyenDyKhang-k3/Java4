package com.fpoly.dao.impl;

import java.util.List;

import com.fpoly.dao.AbstractDao;
import com.fpoly.dao.HistoryDao;
import com.fpoly.entity.History;
import com.fpoly.entity.Video;

public class HistoryDaoImpl extends AbstractDao<History> implements HistoryDao {

	@Override
	public List<History> findByUser(String username) {
		String sql = "SELECT o FROM History o WHERE o.user.username = ?0 AND o.video.isActive = 1"
				+ " ORDER BY o.viewedDate DESC";
		return super.findMany(History.class, sql, username);
	}

	//lấy các danh sách video người dùng đã like với đk isActive = 1
	@Override
	public List<History> findByUserAndIsLiked(String username) {
		String sql = "SELECT o FROM History o"
				+ " WHERE o.user.username = ?0"
				+ " AND o.video.isActive = 1"
				+ " AND o.isLiked = 1"
				+ " ORDER BY o.viewedDate DESC";
		return super.findMany(History.class, sql, username);
	}

	@Override
	public History findByUserIdAndVideoId(Integer userId, Integer videoId) {
		String sql = "SELECT o FROM History o"
				+ " WHERE o.user.id = ?0"
				+ " AND o.video.id = ?1"
				+ " AND o.video.isActive = 1"
				+ " ORDER BY o.viewedDate DESC";
		return super.findOne(History.class, sql, userId, videoId);
	}


}
