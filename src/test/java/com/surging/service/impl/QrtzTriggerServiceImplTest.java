package com.surging.service.impl;

import com.alibaba.fastjson.JSON;
import com.surging.SurgingApplication;
import com.surging.entity.quartzEntity.QrtzTriggers;
import com.surging.repository.QrtzTriggerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by zhangdongmao on 2019/1/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SurgingApplication.class)
@WebAppConfiguration
public class QrtzTriggerServiceImplTest {
    @Autowired
    QrtzTriggerRepository qrtzTriggerRepository;
    @Test
    public void listQrtzTrigger() throws Exception {
        List<QrtzTriggers> qrtzTriggers= qrtzTriggerRepository.listTrigger();
        String json = JSON.toJSONString(qrtzTriggers);
        List<QrtzTriggers> students = JSON.parseArray(json,QrtzTriggers.class);
        System.out.println(students.get(0).getJobName());
        System.out.println(json);
    }

}