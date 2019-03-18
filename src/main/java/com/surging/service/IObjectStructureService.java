package com.surging.service;

import com.surging.entity.ObjectStructure;
import org.springframework.stereotype.Service;

/**
 * Created by zhangdongmao on 2019/1/25.
 */
@Service
public interface IObjectStructureService {
    void insertByObject(ObjectStructure objectStructure);
}
