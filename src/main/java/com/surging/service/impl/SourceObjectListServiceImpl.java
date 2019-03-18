package com.surging.service.impl;

import com.surging.entity.SourceObjectList;
import com.surging.repository.SourceObjectListRepository;
import com.surging.service.ISourceObjectListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangdongmao on 2019/3/12.
 */
@Service
public class SourceObjectListServiceImpl implements ISourceObjectListService {
    @Autowired
    SourceObjectListRepository sourceObjectListRepository;
    @Override
    public List<SourceObjectList> selectAll() {
        return sourceObjectListRepository.selectAll();
    }
}
