package com.surging.ipcTest;

import java.io.IOException;

/**
 * Created by zhangdongmao on 2019/2/21.
 */
public interface Echo {
    public String who() throws IOException;;

    public void from(String name) throws IOException;;
}
