package com.surging.repository;

import com.surging.entity.SourceObjectInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by zhangdongmao on 2019/1/15.
 */
@Mapper
public interface SourceObjectInfoRepository {
    @Insert("insert into source_object_info(sys_abbreviation,object_name,owner,object_type,create_dt,database_type,unique_name,level) values(#{sysAbbreviation},#{objectName},#{owner},#{objectType},#{createDt},#{databaseType},#{uniqueName},#{level})")
    void insertByObject(SourceObjectInfo sourceObjectInfo);
    @Insert("<script>" +
            "insert into source_object_info(sys_abbreviation,object_name,owner,object_type,create_dt,database_type,unique_name,level) values" +
            "<foreach collection='list' item='item' index='index'  separator=','>" +
            "(#{item.sysAbbreviation},#{item.objectName},#{item.owner},#{item.objectType},#{item.createDt},#{item.databaseType},#{item.uniqueName},#{item.level})"+
            "</foreach>" +
            "</script>")
    void insertByList(@Param("list") List<SourceObjectInfo> sourceObjectInfos);
    @Select("select object_name,sys_abbreviation,owner from source_object_info")
    List<SourceObjectInfo> selectTableCoordinate();
    @Select("select * from source_object_info where unique_name=#{uniqueName}")
    SourceObjectInfo selectAllByUniqueName(String uniqueName);
    @Select("select * from source_object_info where level=#{level}")
    List<SourceObjectInfo> selectAllByLevel(int level);
    @Update("update source_object_info set owner=#{owner} , object_type=#{objectType} , unique_name=#{uniqueName} where id=#{id}")
    void updateOwnerAndTypeAndUniqueName(SourceObjectInfo sourceObjectInfo);
    @Update("<script>" +
            "update source_object_info set " +
            "<foreach collection='list' item='item' index='index'  separator=','>" +
            "owner=#{item.owner} , object_type=#{item.objectType} , unique_name=#{item.uniqueName} where id=#{item.id}" +
            "</foreach>" +
            "</script>")
    void updateOwnerAndTypeAndUniqueNameList(@Param("list") List<SourceObjectInfo> sourceObjectInfos);
    @Update("update source_object_info set data_volume=#{dataVolume} where unique_name=#{uniqueName}")
    void updateVolume(SourceObjectInfo sourceObjectInfo);
}
