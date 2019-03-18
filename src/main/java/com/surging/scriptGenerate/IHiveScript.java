package com.surging.scriptGenerate;

import org.springframework.stereotype.Service;

/**
 * Created by zhangdongmao on 2019/2/19.
 */
@Service
public interface IHiveScript {
    void cumulativeVolume(String uniqueName);

}
