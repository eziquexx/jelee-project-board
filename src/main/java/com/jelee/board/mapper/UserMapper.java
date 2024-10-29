package com.jelee.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jelee.board.model.Role;
import com.jelee.board.model.User;

@Mapper
public interface UserMapper {
	void save (User user);
	User findByUserId(String userId);
	// mybatis는 xml에서 resultType을 boolean으로 못하기 때문에 1, 0 으로 값을 가져와야한다.
	// 1, 0 정수 값을 가져오려면 select 구문에서 count 기준으로 했다.
	// 그래서 xml에서 resultType을 Integer로 하고 
	// mapper 인터페이스에서 해당 메서드를 Integer 타입으로 변경.
	Integer checkUserId(String userId);
	void insertUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);
}
