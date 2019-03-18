package com.surging.scriptGenerate.scriptGenerateImpl;

import com.surging.entity.OdsObjectMetadata;
import com.surging.repository.OdsObjectMetadataRepository;
import com.surging.scriptGenerate.IHiveScript;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zhangdongmao on 2019/2/19.
 */

public class HiveScriptImpl implements IHiveScript {
    @Autowired
    OdsObjectMetadataRepository odsObjectMetadataRepository;

    @Override
    public void cumulativeVolume(String uniqueName) {
        OdsObjectMetadata odsObjectMetadata=odsObjectMetadataRepository.selectByUniqueNamePro(uniqueName);

        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("set hive.exec.dynamic.partition=true;\n")
                     .append("set hive.exec.dynamic.partition.mode=nonstrict;\n")
                     .append("set hive.exec.max.dynamic.partitions.pernode = 1000;\n");
    }
}
