package com.surging.repository;

import com.surging.entity.SourceStructure;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangdongmao on 2019/3/17.
 */
@Mapper
public interface SourceStructureRepository {
    @Insert("<script>" +
            "insert into source_structure(unique_name,column_name,column_type,data_length,data_precision,data_scale,is_nullable,comment) values" +
            "<foreach collection='list' item='item' index='index'  separator=','>" +
            "(#{item.uniqueName},#{item.columnName},#{item.columnType},#{item.dataLength},#{item.dataPrecision},#{item.dataScale},#{item.isNullable},#{item.comment})" +
            "</foreach>" +
            "</script>")
    void insertByList(@Param("list") List<SourceStructure> sourceStructure);
    @Delete("delete from source_structure where unique_name=#{uniqueName}")
    void deleteByUniqueName(String uniqueName);
}
