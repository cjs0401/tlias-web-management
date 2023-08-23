package com.itheima.service.impl;

import com.itheima.mapper.DeptLogMapper;
import com.itheima.pojo.DeptLog;
import com.itheima.service.DeptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jsc
 * @version 1.0
 */

@Service
public class DeptLogServiceImpl implements DeptLogService {

//    @Autowired
    private DeptLogMapper deptLogMapper;

    public DeptLogMapper getDeptLogMapper() {
        return deptLogMapper;
    }

    public void setDeptLogMapper(DeptLogMapper deptLogMapper) {
        this.deptLogMapper = deptLogMapper;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insertLog(DeptLog deptLog) {
        deptLogMapper.insertLog(deptLog);
    }

}
