package com.surging.service.impl;

import com.surging.entity.SourceObjectInfo;
import com.surging.repository.SourceObjectInfoRepository;
import com.surging.service.ISourceObjectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangdongmao on 2019/3/7.
 */
@Service
public class SourceObjectInfoServiceImpl implements ISourceObjectInfoService{
    @Autowired
    SourceObjectInfoRepository sourceObjectInfoRepository;

    @Override
    public SourceObjectInfo selectAllByUniqueName(String uniqueName) {
        return sourceObjectInfoRepository.selectAllByUniqueName(uniqueName);
    }

    @Override
    public List<SourceObjectInfo> selectAllByLevel(int level) {
        return sourceObjectInfoRepository.selectAllByLevel(level);
    }
}
