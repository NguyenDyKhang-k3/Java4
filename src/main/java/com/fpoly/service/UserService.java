package com.fpoly.service;

import java.util.List;

import com.fpoly.dto.UserDto;
import com.fpoly.entity.User;

public interface UserService {

	User findById(Integer id);

	User findByEmail(String email);

	User findByUsername(String username);

	User login(String username, String password);

	User resetPassword(String email, String password);

	User changePassword(String username, String newPass);

	List<User> findAll();

	List<User> findAll(int pageNumber, int pageSize);

	User register(String username, String password, String email);

	User create(String email, String password, String username);

	User update(User entity);

	User delete(String username);

	List<UserDto> findUsersLikedVideoByVideoHref(String href);
}
