package com.surging.repository;

import com.surging.entity.ObjectStructure;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zhangdongmao on 2019/1/25.
 */
@Mapper
public interface ObjectStructureRepository {
    @Insert("insert into pro_object_structure(sys_abbreviation,object_name,unique_name,col_name,col_type,partition_key) values(#{sysAbbreviation},#{objectName},#{uniqueName},#{colName},#{colType},#{partitionKey})")
    void insertByObjectFromPro(ObjectStructure objectStructure);
    @Select("select distinct database_name,unique_name from pro_object_structure limit 1,3")
    List<ObjectStructure> selectCoordinateFromPro();
    @Select("select * from pro_object_structure where unique_name=#{uniqueName}")
    List<ObjectStructure> selectAllByUniNameFromPro(String uniqueName);
}
