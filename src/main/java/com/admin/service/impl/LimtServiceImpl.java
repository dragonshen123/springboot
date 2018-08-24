package com.admin.service.impl;

import com.admin.dao.LimitDao;
import com.admin.dao.base.BaseDao;
import com.admin.service.base.BaseServiceImpl;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LimtServiceImpl extends BaseServiceImpl{
    @Autowired
    LimitDao limitDao;
    @Override
    public BaseDao getEntilDao() {
        return limitDao;
    }

    @Override
    public String getTableName() {
        return "syslimit";
    }

    @Override
    public String tableNameId() {
        return "limitId";
    }

}
