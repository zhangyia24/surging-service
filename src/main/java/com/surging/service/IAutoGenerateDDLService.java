package com.surging.service;

import org.springframework.stereotype.Service;

/**
 * Created by zhangdongmao on 2019/3/6.
 */
@Service
public interface IAutoGenerateDDLService {
    String autoGenerateDDLForHive(String UniqueName);
}
