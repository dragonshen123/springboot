package com.admin.dao.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BaseDao {
    /**
     * @param insertOneByObjectSql
     * @return
     */
    public Integer insertOneByObject(@Param(value = "insertOneByObjectSql")String insertOneByObjectSql);

    /**
     * @param insertListByListSql
     * @return
     */
    public Integer insertListByList(@Param(value = "insertListByListSql")String insertListByListSql);

    /**
     * @param insertSql
     * @return
     */
    public Integer insertBySql(@Param(value = "insertSql")String insertSql);

    /**
     * @param deleteOneId
     * @return
     */
    public Integer deleteOneById(@Param(value = "deleteOneId")String deleteOneId);

    /**
     * @param deleteSql
     * @return
     */
    public Integer deleteBySql(@Param(value = "deleteSql")String deleteSql);

    /**
     * @param updateObjectSql
     * @return
     */
    public Integer updateOneByObject(@Param(value = "updateObjectSql")String updateObjectSql);

    /**
     * @param updateListSql
     * @return
     */
    public Integer updateListByList(@Param(value = "updateListSql")String updateListSql);

    /**
     * @param updateSql
     * @return
     */
    public Integer updateBySql(@Param(value = "updateSql")String updateSql);

    /**
     * @param queryOneByIdSql
     * @return
     */
    public Map<String,Object> queryOneById(@Param(value = "queryOneByIdSql")Object queryOneByIdSql);

    /**
     * @return
     */
    public List<Map<String,Object>> queryBySql(@Param(value = "querySql") String querySql);
}
