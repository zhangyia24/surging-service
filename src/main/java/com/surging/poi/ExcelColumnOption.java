package com.surging.poi;

import com.surging.entity.ExcelObject;

/**
 * Created by Administrator on 2018/11/2.
 */
public class ExcelColumnOption {
    public static ExcelObject excelColumnFilter(ExcelObject excelObject,String useFulColumns){
        String[] columnArray = useFulColumns.split(",");
        ExcelObject excelObjectFilter=new ExcelObject();
        for(int i=0;i<columnArray.length;i++){
            excelObjectFilter.addColumn(columnArray[i],excelObject.getColumn(columnArray[i]));
        }
        return excelObjectFilter;
    }
}
