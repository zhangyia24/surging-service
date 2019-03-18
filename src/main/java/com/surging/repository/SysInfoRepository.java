package com.surging.repository;

import com.surging.entity.SysInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zhangdongmao on 2019/1/12.
 */
@Mapper
public interface SysInfoRepository {
    @Insert("insert into sys_info(sys_abbreviation,sys_name,database_type,ip,port,db_username,db_password,sys_type,sid) values(#{sysAbbreviation},#{sysName},#{databaseType},#{ip},#{port},#{dbUsername},#{dbPassword},#{sysType},#{sid})")
    void insertByObject(SysInfo sysInfo);
    @Select("select id,sys_abbreviation,sys_name,database_type,ip,port,db_username,db_password,sys_type from sys_info")
    List<SysInfo> selectAllFromSysInfo();
    @Delete("delete from sys_info where id = #{id}")
    void deleteById(int id);
    @Select("select * from sys_info where sys_abbreviation = #{sysAbbreviation}")
    SysInfo selectBySysAbbreviation(String sysAbbreviation);
}
