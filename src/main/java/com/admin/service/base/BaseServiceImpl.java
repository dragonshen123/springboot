package com.admin.service.base;

import com.admin.dao.base.BaseDao;
import com.admin.service.base.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import utils.ResultJson;
import utils.mybatisSqlUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

public  abstract class BaseServiceImpl implements BaseService {
     public abstract BaseDao getEntilDao();
     public abstract String getTableName();
    public abstract String tableNameId();

    /**将单个对象封装成sql语句插入到数据库中 属性为空时不插入
     * @param object
     * @return 返回null 插入是失败
     */
    @Override
    public Integer insertOneByObject(Object object) {
       try{
           //生成SQL语句
           mybatisSqlUtils  sqlUtils = new mybatisSqlUtils();
           String insertSql=sqlUtils.insertIntoOne(object,getTableName(),tableNameId());
           if(insertSql==null||insertSql.trim().length()<0){
               return null;
           }
           return getEntilDao().insertOneByObject(insertSql);
       }catch (Exception e){
           e.printStackTrace();
           return null;
       }

    }

    /**将单个对象封装成sql语句插入到数据库中 属性为空时不插入
     * @param object
     * @return status 200 操作成功 201 数据为空 500操作失败
     */
    @Override
    public ResultJson insertOneByObjectReJson(Object object) {
        ResultJson resultJson = new ResultJson();
        try{
            //生成SQL语句
            mybatisSqlUtils  sqlUtils = new mybatisSqlUtils();
            String insertSql=sqlUtils.insertIntoOne(object,getTableName(),tableNameId());
            if(insertSql==null||insertSql.trim().length()<0){
                resultJson.setStatus(201);
                resultJson.setMessage("数据不存在!请重试");
                return resultJson;
            }
             getEntilDao().insertOneByObject(insertSql);
            resultJson.setStatus(200);
            resultJson.setMessage("操作成功");
        }catch (Exception e){
            e.printStackTrace();
            resultJson.setStatus(500);
            resultJson.setMessage("操作失败!请重试或者联系管理员");
        }
        return resultJson;
    }

    /**将多个对象封装成sql语句插入到数据库中 注意：只有集合数据一致是才能插入 其他另写
     * @param list
     * @return
     */
    @Override
    public Integer insertListByList(List<Object> list)
    {
        try{
            //生成SQL语句
            mybatisSqlUtils  sqlUtils = new mybatisSqlUtils();
            String insertSql=sqlUtils.insertIntoMore(list,getTableName());
            if(insertSql==null||insertSql.trim().length()<0){
                return null;
            }
            return getEntilDao().insertListByList(insertSql);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param list
     * @return
     */
    @Override
    public ResultJson insertListByListReJson(List<Object> list) {
        ResultJson resultJson =new ResultJson();
        try{
            //生成SQL语句
            mybatisSqlUtils  sqlUtils = new mybatisSqlUtils();
            String insertSql=sqlUtils.insertIntoMore(list,getTableName());
            if(insertSql==null||insertSql.trim().length()<0){
                resultJson.setMessage("数据不存在！请重试");
                resultJson.setStatus(201);
                return resultJson;
            }
             getEntilDao().insertListByList(insertSql);
            resultJson.setMessage("操作成功");
            resultJson.setStatus(202);
        }catch (Exception e){
            e.printStackTrace();
            resultJson.setMessage("操作失败!请重试或者联系管理员");
            resultJson.setStatus(500);
        }
        return resultJson;
    }

    /**通过sql语句插入到数据库中
     * @param insertSql
     * @return
     */
    @Override
    public Integer insertBySql(String insertSql) {
        try{
            //生成SQL语句
            if(insertSql==null||insertSql.trim().length()<0){
                return null;
            }
            return getEntilDao().insertBySql(insertSql);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param insertSql
     * @return
     */
    @Override
    public ResultJson insertBySqlReJson(String insertSql) {
        ResultJson resultJson = new ResultJson();
        try{
            //生成SQL语句
            if(insertSql==null||insertSql.trim().length()<0){
                resultJson.setStatus(201);
                resultJson.setMessage("数据不存在");
                return resultJson;
            }
             getEntilDao().insertBySql(insertSql);
            resultJson.setStatus(200);
            resultJson.setMessage("操作成功");
        }catch (Exception e){
            e.printStackTrace();
            resultJson.setStatus(500);
            resultJson.setMessage("操作失败!请重试或者联系管理员");
        }
        return resultJson;
    }

    /**根据数据库字段id的值删除数据
     * @param id
     * @return
     */
    @Override
    public Integer deleteOneById(Object id) {
        try{
            String deleteSql=" DELETE FROM "+getTableName()+" WHERE "+tableNameId();
            if (!(id instanceof String)) {
                deleteSql+=" = "+id;
            }else{
                deleteSql+=" = '"+id+"'";
            }
            return getEntilDao().deleteOneById(deleteSql);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ResultJson deleteOneByIdReJson(Object id) {
        ResultJson resultJson = new ResultJson();
        try{
            String deleteSql=" DELETE FROM "+getTableName()+" WHERE "+tableNameId();
            if (!( id instanceof String||id instanceof Date ||id instanceof java.sql.Date )) {
                deleteSql+=" = "+id;
            }else{
                deleteSql+=" = '"+id+"'";
            }
             getEntilDao().deleteOneById(deleteSql);
            resultJson.setStatus(200);
            resultJson.setMessage("操作成功");
        }catch (Exception e){
            e.printStackTrace();
            resultJson.setStatus(500);
            resultJson.setMessage("操作失败！请重试或者联系管理员");
        }
        return resultJson;
    }

    /**根据时语句删除数据库记录
     * @param deleteSql
     * @return
     */
    @Override
    public Integer deleteBySql(String deleteSql) {
        try{
            return getEntilDao().deleteBySql(deleteSql);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * @param deleteSql
     * @return
     */
    @Override
    public ResultJson deleteBySqlReJson(String deleteSql) {
        ResultJson resultJson = new ResultJson();
        try{
             getEntilDao().deleteBySql(deleteSql);
            resultJson.setStatus(200);
            resultJson.setMessage("操作成功");
        }catch (Exception e){
            e.printStackTrace();
            resultJson.setStatus(500);
            resultJson.setMessage("操作失败！请重试或者联系管理员");
        }
        return resultJson;
    }

    /**蒋单个对象生产sql语句插入到数据库中
     * @param updateObject
     * @return
     */
    @Override
    public Integer updateOneByObject(Object updateObject) {
        try{
            mybatisSqlUtils  sqlUtils = new mybatisSqlUtils();
            String insertSql=sqlUtils.updateOneByObj(updateObject,getTableName(),tableNameId());
            return getEntilDao().updateOneByObject(insertSql);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * @param updateObject
     * @return
     */
    @Override
    public ResultJson updateOneByObjectReJson(Object updateObject) {
        ResultJson resultJson = new ResultJson();
        try{
            mybatisSqlUtils  sqlUtils = new mybatisSqlUtils();
            String insertSql=sqlUtils.updateOneByObj(updateObject,getTableName(),tableNameId());
             getEntilDao().updateOneByObject(insertSql);
            resultJson.setStatus(200);
            resultJson.setMessage("操作成功");
        }catch (Exception e){
            e.printStackTrace();
            resultJson.setStatus(500);
            resultJson.setMessage("操作失败！请重试或者联系管理员");
        }
        return resultJson;
    }

    /**将多个对象封装成sql语句插入到数据库中
     * @param uodateList
     * @return
     */
    @Override
    public Integer updateListByList(List<Object> uodateList) {
        try{
            mybatisSqlUtils  sqlUtils = new mybatisSqlUtils();
            String insertSql=sqlUtils.updateBatchSql(uodateList,getTableName(),tableNameId());
            return getEntilDao().updateListByList(insertSql);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param updateList
     * @return
     */
    @Override
    public ResultJson updateListByListReJson(List<Object> updateList) {
        ResultJson resultJson = new ResultJson();
        try{
            mybatisSqlUtils  sqlUtils = new mybatisSqlUtils();
            String insertSql=sqlUtils.updateBatchSql(updateList,getTableName(),tableNameId());
             getEntilDao().updateListByList(insertSql);
            resultJson.setStatus(200);
            resultJson.setMessage("操作成功");
        }catch (Exception e){
            e.printStackTrace();
            resultJson.setStatus(500);
            resultJson.setMessage("操作失败！请重试或者联系管理员");
        }
        return resultJson;
    }

    /**通过sql语句更新数据库
     * @param updateSql
     * @return
     */
    @Override
    public Integer updateBySql(String updateSql) {
        try{
            if(updateSql==null||updateSql.trim().length()<0){
                return null;
            }
            return getEntilDao().updateBySql(updateSql);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param updateSql
     * @return
     */
    @Override
    public ResultJson updateBySqlReJson(String updateSql) {
        ResultJson resultJson = new ResultJson();
        try{
            if(updateSql==null||updateSql.trim().length()<0){
                resultJson.setStatus(201);
                resultJson.setMessage("数据为空");
                return resultJson;
            }
             getEntilDao().updateBySql(updateSql);
            resultJson.setStatus(200);
            resultJson.setMessage("操作陈功");
        }catch (Exception e){
            e.printStackTrace();
            resultJson.setStatus(200);
            resultJson.setMessage("操作失败！请重试或者联系管理员");
        }
        return resultJson;
    }

    /**通过id查询数据库库
     * @param id
     * @return
     */
    @Override
    public Map<String,Object> queryOneById(Object id) {
        try{
            if(id==null){
                return null;
            }
            String querySql="select * from "+getTableName() +"  where "+ tableNameId()+" = ";
            if (!(id instanceof String)) {
                querySql+=id;
            }else{
                querySql+=" '"+id+"'";
            }
            return getEntilDao().queryOneById(querySql);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /**通过sql查询数据库
     * @param querySql
     * @return
     */
    @Override
    public List<Map<String,Object>> queryBySql(String querySql) {
        try{
            if(querySql==null){
                return null;
            }
            return getEntilDao().queryBySql(querySql);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**分页查询
     * @param pageSql 分页查询sql语句 不包括分页语句和排序 select * from testTable where -----
     * @param pageNum  当前页 1
     * @param pageSize  分页大小  4
     * @param orderByStr  排序语句 id asc
     * @return
     */
    @Override
    public PageInfo pageQuery(String pageSql,Integer pageNum,Integer pageSize,String orderByStr){
        try {
            PageHelper.startPage(pageNum, pageSize);
            PageHelper.orderBy(orderByStr);
            PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(getEntilDao().queryBySql(pageSql));
            return page;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
