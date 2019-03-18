package com.surging.service.impl;

import com.surging.entity.quartzEntity.QrtzTriggers;
import com.surging.repository.QrtzTriggerRepository;
import com.surging.service.IQrtzTriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangdongmao on 2019/1/17.
 */
@Service
public class QrtzTriggerServiceImpl implements IQrtzTriggerService {
    @Autowired
    QrtzTriggerRepository qrtzTriggerRepository;


    @Override
    public List<QrtzTriggers> listQrtzTrigger() {
        return qrtzTriggerRepository.listTrigger();
    }
}
