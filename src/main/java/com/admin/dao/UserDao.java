package com.admin.dao;
import com.admin.dao.base.BaseDao;

import java.util.List;
import java.util.Map;
public interface UserDao extends BaseDao {
    public  List<Map<String,Object>> query();
}
