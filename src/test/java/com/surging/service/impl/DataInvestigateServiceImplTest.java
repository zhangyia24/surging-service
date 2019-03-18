package com.surging.service.impl;

import com.surging.SurgingApplication;
import com.surging.dao.DataInvestigateDao;
import com.surging.entity.SourceObjectInfo;
import com.surging.entity.SourceStructure;
import com.surging.repository.SourceStructureRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhangdongmao on 2019/3/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SurgingApplication.class)
@WebAppConfiguration
public class DataInvestigateServiceImplTest {
    @Autowired
    DataInvestigateDao dataInvestigateDao;
    @Autowired
    SourceStructureRepository sourceStructureRepository;
    @Test
    public void getIndexAndPrimary() throws Exception {
        SourceObjectInfo sourceObjectInfo=new SourceObjectInfo();
        sourceObjectInfo.setUniqueName("surging_oracle_surging_cx_awk_dlr_plan_prd");
        sourceObjectInfo.setObjectName("CX_AWK_DLR_PLAN_PRD");
        sourceObjectInfo.setOwner("surging");
        sourceObjectInfo.setSysAbbreviation("surging_oracle");
        sourceObjectInfo.setDatabaseType("oracle");
        List<SourceStructure> sourceStructureList=dataInvestigateDao.getColumnInfo(sourceObjectInfo);
        sourceStructureRepository.insertByList(sourceStructureList);
    }

}