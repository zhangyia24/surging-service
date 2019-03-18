package com.surging.service;

import com.surging.entity.Administrator;
import com.surging.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by zhangdongmao on 2019/1/13.
 */
@Service
public class AdministratorService {
    @Autowired
    AdministratorRepository administratorRepository;

    public Administrator selectByAdminMap(Map<String,String> adminMap){
        Administrator administrator=administratorRepository.selectByadminMap(adminMap);
        return administrator;
    }

}
