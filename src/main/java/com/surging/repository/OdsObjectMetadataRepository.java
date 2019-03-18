package com.surging.repository;

import com.surging.entity.OdsObjectMetadata;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zhangdongmao on 2019/1/26.
 */
@Mapper
public interface OdsObjectMetadataRepository {
    @Insert("insert into pro_ods_object_metadata (database_name,unique_name,partitions,field_terminate,line_terminate,comment,split_by,extract_type,increment_column,extract_days) values " +
            "(#{databaseName},#{uniqueName},#{partitions},#{fieldTerminate},#{lineTerminate},#{comment},#{splitBy},#{extractType},#{incrementColumn},#{extractDays})")
    void insertByObject(OdsObjectMetadata odsObjectMetadata);
    @Select("select database_name,unique_name,partitions,field_terminate,line_terminate,comment,split_by,extract_type,increment_column,extract_days from pro_ods_object_metadata where unique_name=#{uniqueName}")
    OdsObjectMetadata selectByUniqueNamePro(String uniqueName);
}
