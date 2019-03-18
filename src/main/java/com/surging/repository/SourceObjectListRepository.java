package com.surging.repository;


import com.surging.entity.SourceObjectInfo;
import com.surging.entity.SourceObjectList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by zhangdongmao on 2019/1/15.
 */
@Mapper
public interface SourceObjectListRepository {
    @Insert("insert into source_object_list(sys_abbreviation,object_name,owner,object_type,create_dt,database_type,unique_name) values(#{sysAbbreviation},#{objectName},#{owner},#{objectType},#{createDt},#{databaseType},#{uniqueName})")
    void insertByObject(SourceObjectList sourceObjectList);
    @Select("select * from source_object_list")
    List<SourceObjectList> selectAll();
    @Update("update source_object_info set owner=#{owner} , object_type=#{objectType} , unique_name=#{uniqueName} where sys_abbreviation=#{sysAbbreviation} and owner=#{owner} and object_type=#{objectType}")
    void updateOwnerAndTypeAndUniqueName(SourceObjectList sourceObjectList);
}
