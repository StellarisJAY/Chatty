package com.jay.chat.dao;

import com.jay.chat.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface UserDAO {
    @Select("SELECT username FROM tb_user WHERE userId=#{userId}")
    String getUsername(@Param("userId") Integer userId);

    @Select("SELECT * FROM tb_user WHERE userId=#{userId}")
    User getUser(@Param("userId") Integer userId);

    @Select("SELECT groupId FROM tb_user_group WHERE userId=#{userId}")
    List<Integer> getUserGroups(@Param("userId") Integer userId);

    @Select("SELECT * FROM tb_user WHERE username=#{username}")
    User getUserByName(@Param("username") String username);
}
