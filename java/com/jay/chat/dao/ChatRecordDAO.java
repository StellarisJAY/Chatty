package com.jay.chat.dao;

import com.jay.chat.model.Message;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ChatRecordDAO {

    @Insert("INSERT INTO tb_message VALUES(null, #{senderId}, #{targetId}, #{type}, #{content}, #{timestamp}, 0)")
    void insertUnreadMessage(Message message);

    @Insert("INSERT INTO tb_message VALUES(null, #{senderId}, #{targetId}, #{type}, #{content}, #{timestamp}, 1)")
    void insertMessage(Message message);

    @Select("SELECT * FROM tb_message " +
            "WHERE (senderId=#{userId} AND targetId=#{friendId}) " +
            "OR (senderId=#{friendId} AND targetId=#{userId}) " +
            "ORDER BY timestamp ASC")
    List<Message> getChatRecords(Long userId, Long friendId);

    @Select("SELECT COUNT(messageId) FROM tb_message WHERE senderId=#{friendId} AND targetId=#{userId} AND hasRead=0")
    Integer countUnreadMessage(Long userId, Long friendId);

    @Delete("DELETE FROM tb_message WHERE messageId=#{messageId} AND senderId=#{userId}")
    Integer deleteMessage(Long messageId, Long userId);

}
