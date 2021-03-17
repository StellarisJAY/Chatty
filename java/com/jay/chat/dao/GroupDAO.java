package com.jay.chat.dao;

import com.jay.chat.model.UserGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GroupDAO {

    @Select("SELECT * FROM tb_group")
    List<UserGroup> getGroups();
}
