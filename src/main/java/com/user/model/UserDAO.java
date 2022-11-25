package com.user.model;

import java.util.List;

public interface UserDAO {
	int createUser(UserVO user);

	int getUserCount(PagingVO pvo);

	List<UserVO> listUser(PagingVO pvo);

	UserVO getUser(Integer midx);

	Integer idCheck(String userid);

	UserVO findUser(UserVO findUser);

	int deleteUser(Integer midx);

	int updateUser(UserVO user);
}
