package com.admin.service.impl;

import com.admin.dao.UserDao;
import com.admin.dao.base.BaseDao;
import com.admin.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "userService")
public class UserServiceImpl extends BaseServiceImpl {
    @Autowired
    UserDao userDao;
    @Override
    public BaseDao getEntilDao() {
        return userDao;
    }

    @Override
    public String getTableName() {
        return "sysuser";
    }

    @Override
    public String tableNameId() {
        return "userId";
    }

    public List<Map<String,Object>> query(){
    return  userDao.query();
 }
    @Cacheable(value="thisredis", key="'redister'")
   public List<Map<String,Object>> radisTest(String sql){
        return userDao.queryBySql(sql);
   }
    @CacheEvict(value="thisredis", key="'redister'")
    public List<Map<String,Object>> radisTestEvict(String sql){
        return null;
    }
}
