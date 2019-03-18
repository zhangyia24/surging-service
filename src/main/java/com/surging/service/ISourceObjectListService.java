package com.surging.service;

import com.surging.entity.SourceObjectList;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangdongmao on 2019/3/12.
 */
@Service
public interface ISourceObjectListService {
    List<SourceObjectList> selectAll();
}
