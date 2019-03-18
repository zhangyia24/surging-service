package com.surging.service;

import com.surging.entity.SourceObjectInfo;
import com.surging.entity.SourceObjectList;

import java.util.List;

/**
 * Created by zhangdongmao on 2019/3/9.
 */
public interface IDataInvestigateService {
    List<SourceObjectInfo> getObjectownerAndTypeAndUniqueName(List<SourceObjectInfo> sourceObjectInfos);
    List<SourceObjectInfo> getDataVolume(List<SourceObjectInfo> sourceObjectInfos);
    void getColumnInfo(List<SourceObjectInfo> sourceObjectInfos);
}
