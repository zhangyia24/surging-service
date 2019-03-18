package com.surging.repository;


import com.surging.entity.User1;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * Created by Song on 2017/2/15.
 * User表操作接口
 */
@Mapper
public interface UserRepository {
    @Delete("drop table if exists user1")
    void dropTable();

    @Insert("create table user1 (id int, age integer, name varchar(255))")
    void createTable();

    @Insert("insert into user1(name,age) values(#{name},#{age})")
    void insert(User1 user1);

    @Select("select id,name,age from user1")
    List<User1> findAll();

    @Select("select id,name,age from user1 where name = 'zdm'")
    List<User1> findByNameLike(String name);


}


