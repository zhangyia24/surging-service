package com.surging.service;

import com.surging.entity.SysInfo;
import com.surging.repository.SysInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangdongmao on 2019/1/13.
 */
@Service
public class SysInfoService {

    @Autowired
    private SysInfoRepository sysInfoRepository;

    public List<SysInfo> selectAllFromSysInfo(){
        List<SysInfo> sysInfos=sysInfoRepository.selectAllFromSysInfo();
        return sysInfos;
    }

    public void deleteByIdFromSYSInfo(int id){
        sysInfoRepository.deleteById(id);
    }

}
