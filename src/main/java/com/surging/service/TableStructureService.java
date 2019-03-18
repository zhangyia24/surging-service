package com.surging.service;

import com.surging.repository.TableStructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/10/22.
 */
@Service
public class TableStructureService {
    @Autowired
    TableStructureRepository tableStructureRepository;

    public List<Map> listTableColumn(String tableName){
        return tableStructureRepository.listTableColumn(tableName);
    }
}
