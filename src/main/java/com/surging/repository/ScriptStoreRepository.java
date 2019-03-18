package com.surging.repository;

import com.surging.entity.ScriptStore;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by zhangdongmao on 2019/1/26.
 */
@Mapper
public interface ScriptStoreRepository {
    @Insert("insert into script_store(unique_name,hive_ddl) values（uniqueName,hiveDdl）")
    void insertByObject(ScriptStore scriptStore);
}
