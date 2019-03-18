package com.surging.repository;

import com.surging.entity.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * Created by zhangdongmao on 2019/1/13.
 */
@Mapper
public interface AdministratorRepository {
    @Select("select * from administrator where username=#{username} and password=#{password}")
    Administrator selectByadminMap(Map<String,String> adminMap);
}
