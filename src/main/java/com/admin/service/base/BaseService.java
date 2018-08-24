package com.admin.service.base;

import com.admin.dao.base.BaseDao;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import utils.ResultJson;

import java.util.List;
import java.util.Map;

public interface BaseService  {
    /**
     * @param object
     * @return
     */
    public Integer insertOneByObject(Object object);


    /**
     * @param object
     * @return
     */
    public ResultJson insertOneByObjectReJson(Object object);

    /**
     * @param list
     * @return
     */
    public Integer insertListByList(List<Object> list);

    /**
     * @param list
     * @return
     */
    public ResultJson insertListByListReJson(List<Object> list);
    /**
     * @param insertSql
     * @return
     */
    public Integer insertBySql(String insertSql);

    /**
     * @param insertSql
     * @return
     */
    public ResultJson insertBySqlReJson(String insertSql);
    /**
     * @param object
     * @return
     */
    public Integer deleteOneById(Object object);

    /**
     * @param object
     * @return
     */
    public ResultJson deleteOneByIdReJson(Object object);
    /**
     * @param deleteSql
     * @return
     */
    public Integer deleteBySql(String deleteSql);

    /**
     * @param deleteSql
     * @return
     */
    public ResultJson deleteBySqlReJson(String deleteSql);
    /**
     * @param updateObject
     * @return
     */
    public Integer updateOneByObject(Object updateObject);

    /**
     * @param updateObject
     * @return
     */
    public ResultJson updateOneByObjectReJson(Object updateObject);
    /**
     * @param uodateList
     * @return
     */
    public Integer updateListByList(List<Object> uodateList);

    /**
     * @param uodateList
     * @return
     */
    public ResultJson updateListByListReJson(List<Object> uodateList);
    /**
     * @param updateSql
     * @return
     */
    public Integer updateBySql(String updateSql);

    /**
     * @param updateSql
     * @return
     */
    public ResultJson updateBySqlReJson(String updateSql);

    /**
     * @param object
     * @return
     */
    public Map<String,Object> queryOneById(Object object);
    /**
     * @return
     */
    public List<Map<String,Object>> queryBySql(@Param(value = "querySql") String querySql);

    /**
     * @param pageSql
     * @param pageNum
     * @param pageSize
     * @param orderByStr
     * @return
     */
    public PageInfo pageQuery(String pageSql, Integer pageNum, Integer pageSize, String orderByStr);
}
