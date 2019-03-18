package com.surging.service.impl;

import com.surging.dao.DataInvestigateDao;
import com.surging.entity.SourceObjectInfo;
import com.surging.entity.SourceObjectList;
import com.surging.entity.SourceStructure;
import com.surging.entity.SysInfo;
import com.surging.repository.SourceObjectInfoRepository;
import com.surging.repository.SourceObjectListRepository;
import com.surging.repository.SourceStructureRepository;
import com.surging.repository.SysInfoRepository;
import com.surging.service.IDataInvestigateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangdongmao on 2019/1/12.
 */
@Service
public class DataInvestigateServiceImpl implements IDataInvestigateService {

    @Autowired
    DataInvestigateDao dataInvestigateDao;
    @Autowired
    SourceObjectInfoRepository sourceObjectInfoRepository;
    @Autowired
    SourceStructureRepository sourceStructureRepository;
    /**
    * @Author: zhangdongmao
    * @Date: 2019/1/15
    * @Description:  获取每张表的属主和类型
    * @Param:
    * @return:
    */
    public List<SourceObjectInfo> getObjectownerAndTypeAndUniqueName(List<SourceObjectInfo> sourceObjectInfos) {
        SourceObjectInfo sourceObjectInfo;
        List<SourceObjectInfo> sourceObjectInfosRests=new ArrayList<>();
        //遍历SourceObjectInfos对象列表
        for (int i=0;i<sourceObjectInfos.size();i++){
            sourceObjectInfo=sourceObjectInfos.get(i);
            //在dao层查找到owner和对象类型，并返回一个新的sourceObjectInfo对象
            sourceObjectInfosRests.addAll(dataInvestigateDao.getObjectownerAndTypeAndUniqueName(sourceObjectInfo));
        }
        for (SourceObjectInfo sourceObjectInfoRest:sourceObjectInfosRests) {
            try {
                sourceObjectInfoRepository.insertByObject(sourceObjectInfoRest);
            }catch (Exception e){
                System.out.println(e.toString());
                System.out.println(e.getMessage());
            }
        }
        return sourceObjectInfosRests;
    }
    /**
    * @Author: zhangdongmao
    * @Date: 2019/3/10
    * @Description:  获取每张表的数据量
    * @Param: * @param null 1
    * @return:
    */
    public List<SourceObjectInfo> getDataVolume(List<SourceObjectInfo> sourceObjectInfos) {
        SourceObjectInfo sourceObjectInfo;
        //遍历SourceObjectInfos对象列表
        for (int i=0;i<sourceObjectInfos.size();i++){
            sourceObjectInfo=sourceObjectInfos.get(i);
            //在dao层查找到owner和对象类型，并返回一个新的sourceObjectInfo对象
            sourceObjectInfo=dataInvestigateDao.getDataVolume(sourceObjectInfo);
            //设置list中当前对象为新返回的对象
            sourceObjectInfos.set(i,sourceObjectInfo);
            sourceObjectInfoRepository.updateVolume(sourceObjectInfo);
        }
        return sourceObjectInfos;
    }

    @Override
    public void getColumnInfo(List<SourceObjectInfo> sourceObjectInfos) {
        SourceObjectInfo sourceObjectInfo;
        //遍历SourceObjectInfos对象列表
        for (int i=0;i<sourceObjectInfos.size();i++){
            sourceObjectInfo=sourceObjectInfos.get(i);
            //在dao层查找到owner和对象类型，并返回一个新的sourceObjectInfo对象
            List<SourceStructure> sourceStructures=dataInvestigateDao.getColumnInfo(sourceObjectInfo);
            sourceStructureRepository.deleteByUniqueName(sourceStructures.get(0).getUniqueName());
            sourceStructureRepository.insertByList(sourceStructures);
        }
    }


    /**
    * @Author: zhangdongmao
    * @Date: 2019/3/12
    * @Description:  获得每张表的主键和索引信息
    * @Param: * @param null 1
    * @return:
    */
    public List<SourceObjectList> getIndexAndPrimary(List<SourceObjectList> sourceObjectLists) {
//        SourceObjectList sourceObjectList;
//        //遍历SourceObjectInfos对象列表
//        for (int i=0;i<sourceObjectLists.size();i++){
//            sourceObjectList=sourceObjectLists.get(i);
//            //在dao层查找到owner和对象类型，并返回一个新的sourceObjectInfo对象
//            sourceObjectList=dataInvestigateDao.getIndexAndPrimary(sourceObjectList);
//            //设置list中当前对象为新返回的对象
//            sourceObjectLists.set(i,sourceObjectList);
//            sourceObjectInfoRepository.updataVolume(sourceObjectList);
//        }
        return sourceObjectLists;
    }
}
