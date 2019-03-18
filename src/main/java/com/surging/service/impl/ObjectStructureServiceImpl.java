package com.surging.service.impl;

import com.surging.entity.ObjectStructure;
import com.surging.repository.ObjectStructureRepository;
import com.surging.service.IObjectStructureService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhangdongmao on 2019/1/25.
 */
public class ObjectStructureServiceImpl implements IObjectStructureService {
    @Autowired
    ObjectStructureRepository objectStructureRepository;
    @Override
    public void insertByObject(ObjectStructure objectStructure) {
        try {
            objectStructureRepository.insertByObjectFromPro(objectStructure);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
