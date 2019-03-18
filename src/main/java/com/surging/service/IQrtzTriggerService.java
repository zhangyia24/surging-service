package com.surging.service;

import com.surging.entity.quartzEntity.QrtzTriggers;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangdongmao on 2019/1/17.
 */
@Service
public interface IQrtzTriggerService {

    List<QrtzTriggers> listQrtzTrigger();
}
