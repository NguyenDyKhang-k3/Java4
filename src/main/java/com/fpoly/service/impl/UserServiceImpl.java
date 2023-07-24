package com.fpoly.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fpoly.constant.namedStored;
import com.fpoly.dao.UserDao;
import com.fpoly.dao.impl.UserDaoImpl;
import com.fpoly.dto.UserDto;
import com.fpoly.entity.User;
import com.fpoly.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao dao;

	public UserServiceImpl() {
		dao = new UserDaoImpl();
	}

	@Override
	public User findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public User findByEmail(String email) {
		return dao.findByEmail(email);
	}

	@Override
	public User findByUsername(String username) {
		return dao.findByUserName(username);
	}

	@Override
	public User login(String username, String password) {
		return dao.findByUserNameAndPassword(username, password);
	}

	@Override
	public User resetPassword(String email, String password) {
		User existUser = findByEmail(email);
		if (existUser != null) {
			existUser.setPassword(password);
			return dao.update(existUser);
		}
		return null;
	}
	
	@Override
	public User changePassword(String username, String newPass) {
		User existUser = findByUsername(username);
		if (existUser != null) {
			existUser.setPassword(newPass);
			return dao.update(existUser);
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		return dao.findAll();
	}

	@Override
	public List<User> findAll(int pageNumber, int pageSize) {
		return dao.findAll(pageNumber, pageSize);
	}

	@Override
	public User register(String username, String password, String email) {
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setEmail(email);
		newUser.setIsAdmin(Boolean.FALSE);
		newUser.setIsActive(Boolean.TRUE);
		return dao.create(newUser);
	}

	@Override
	public User update(User entity) {
		return dao.update(entity);
	}

	@Override
	public User delete(String username) {
		User user = dao.findByUserName(username);
		user.setIsActive(Boolean.FALSE);
		return dao.update(user);
	}

	@Override
	public List<UserDto> findUsersLikedVideoByVideoHref(String href) {
		Map<String, Object> params = new HashMap<>();
		params.put(namedStored.Videohref, href);
		List<User> users = dao.findUsersLikedVideoByVideoHref(params);
		List<UserDto> result =  new ArrayList<>();
		users.forEach(user -> {
			UserDto dto = new UserDto();
			dto.setUsername(user.getUsername());
			dto.setEmail(user.getEmail());
			result.add(dto);
		});
		return result;
	}

}
