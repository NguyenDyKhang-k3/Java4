package com.fpoly.dao.impl;

import java.util.List;
import java.util.Map;

import com.fpoly.constant.namedStored;
import com.fpoly.dao.AbstractDao;
import com.fpoly.dao.UserDao;
import com.fpoly.entity.User;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

	@Override
	public User findById(Integer id) {
		return super.findById(User.class, id);
	}

	@Override
	public User findByEmail(String email) {
		String sql = "SELECT o FROM User o WHERE o.email = ?0";
		return super.findOne(User.class, sql, email);
	}

	@Override
	public User findByUserName(String username) {
		String sql = "SELECT o FROM User o WHERE o.username = ?0";
		return super.findOne(User.class, sql, username);
	}

	@Override
	public User findByUserNameAndPassword(String username, String password) {
		String sql = "SELECT o FROM User o WHERE o.username = ?0 AND o.password =?1";
		return super.findOne(User.class, sql, username, password);
	}

	@Override
	public List<User> findAll() {
		return super.findAll(User.class, true);
	}

	@Override
	public List<User> findAll(int pageNumber, int pageSize) {
		return super.findAll(User.class, true, pageNumber, pageSize);
	}

	@Override
	public User create(User entity) {
		return super.create(entity);
	}

	@Override
	public User update(User entity) {
		return super.update(entity);
	}

	@Override
	public User delete(User entity) {
		return super.delete(entity);
	}

	@Override
	public List<User> findUsersLikedVideoByVideoHref(Map<String, Object> params) {
		return super.callStored(namedStored.FIND_USERS_LIKED_VIDEO_BY_VIDEO_HREF, params);
	}

}
