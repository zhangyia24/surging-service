package com.surging.service;

import com.surging.entity.OdsObjectMetadata;
import org.springframework.stereotype.Service;

/**
 * Created by zhangdongmao on 2019/2/23.
 */
@Service
public interface IImportService {
    void sqoopImport(OdsObjectMetadata odsObjectMetadata);
}
