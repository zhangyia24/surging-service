package com.surging.tools;


import org.apache.commons.lang3.StringUtils;

/**
 * Created by zhangdongmao on 2019/1/17.
 */
public class EmptyUtil {
    public static String isEmpty(String... characters){
        for (String character:characters ){
            if(StringUtils.isEmpty(character)){
                return character+"is null";
            }
        }
        return "";
    }
}
