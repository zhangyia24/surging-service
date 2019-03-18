package com.surging.service;

import com.surging.entity.SourceObjectInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangdongmao on 2019/3/7.
 */
@Service
public interface ISourceObjectInfoService {
    SourceObjectInfo selectAllByUniqueName(String uniqueName);
    List<SourceObjectInfo> selectAllByLevel(int level);
}
