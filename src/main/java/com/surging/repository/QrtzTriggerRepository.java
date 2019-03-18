package com.surging.repository;

import com.surging.entity.quartzEntity.QrtzTriggers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zhangdongmao on 2019/1/17.
 */
@Mapper
public interface QrtzTriggerRepository {
    @Select("select job_name,job_group,prev_fire_time,next_fire_time,trigger_state,trigger_type from qrtz_triggers")
    List<QrtzTriggers> listTrigger();
}
