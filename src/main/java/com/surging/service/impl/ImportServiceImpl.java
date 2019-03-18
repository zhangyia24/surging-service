package com.surging.service.impl;

import com.surging.entity.OdsObjectMetadata;
import com.surging.repository.OdsObjectMetadataRepository;
import com.surging.service.IImportService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhangdongmao on 2019/2/23.
 */
public class ImportServiceImpl implements IImportService {
    @Autowired
    OdsObjectMetadataRepository odsObjectMetadataRepository;
    @Override
    public void sqoopImport(OdsObjectMetadata odsObjectMetadata) {

    }
}
