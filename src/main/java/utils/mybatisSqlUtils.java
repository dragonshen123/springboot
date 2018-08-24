package utils;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.reflect.FieldUtils;


import javax.xml.crypto.Data;

public class mybatisSqlUtils {
	
 //生成插入语句
	public  String insertIntoOne(Object obj, String tableName,String objName) {
	     if(null == obj) return null;
	     StringBuilder filedSql = new StringBuilder("(");
	     StringBuilder valueSql = new StringBuilder(" (");
	     Field[] fields = obj.getClass().getDeclaredFields();
	     for (int i = 0; i < fields.length; i++) {
			 Object value= mybatisSqlUtils.readField(fields[i],obj);
			 if(value!=null) {
				 filedSql.append(fields[i].getName() + ",");
				 if ( value instanceof String||value instanceof Date ||value instanceof java.sql.Date ){
					 valueSql.append("'" + value + "',");
				 }else{
					 valueSql.append("" + value + ",");
				 }

			 }
	     }
	     valueSql.deleteCharAt(valueSql.length() - 1);
	     filedSql.deleteCharAt(filedSql.length() - 1);
	     valueSql.append(") ");
	     filedSql.append(") ");
	     return "INSERT INTO "+tableName+" "+filedSql.toString()+"VALUES "+valueSql.toString();
	 }
	//批量插入语句
	public  String insertIntoMore(List<Object> list, String tableName){
	     if(null == list||list.size()<=0) return null;
	     StringBuilder filedSql = new StringBuilder("(");
	     Field[] fields = list.get(0).getClass().getDeclaredFields();
	     //字段的拼接
	     for (int i = 0; i < fields.length; i++) {
	    	 Object value = mybatisSqlUtils.readField(fields[i], list.get(0));
	    	 if(value!=null){
	         filedSql.append(fields[i].getName() + ",");
	    	 }
	     }
	     filedSql.deleteCharAt(filedSql.length() - 1);
	     filedSql.append(")");
	    
	     //值得拼接
	     StringBuilder valueSql = new StringBuilder(" (");
	     for (int i = 0; i < list.size(); i++) {
	    	if(list.get(i)!=null){
	    	 Field[] fieldsvalue = list.get(i).getClass().getDeclaredFields();
	      for (int j = 0; j < fieldsvalue.length; j++) {
	    	  Object value = mybatisSqlUtils.readField(fields[j], list.get(i));
	    	   if(value!=null){
	    	   if ( value instanceof String||value instanceof Date ||value instanceof java.sql.Date ) {
	    		  valueSql.append("'"+value+"',");
				}else{
				   valueSql.append(value+",");
			   }
	    	   }
	      }
	    	}
	      valueSql.deleteCharAt(valueSql.length() - 1);
		  valueSql.append("),(");
	     }
	     valueSql.deleteCharAt(valueSql.length() - 1);
	     valueSql.deleteCharAt(valueSql.length() - 1);
		String sql ="INSERT INTO "+tableName+" "+filedSql.toString()+"VALUES"+valueSql.toString();
		return  sql;
	}
	 //获取字段的值
    public static  Object readField(Field field,Object target){  
        try {  
            return FieldUtils.readField( field , target, true )  ;   
        } catch (Exception e) {  
            e.printStackTrace();
            return null;
        }  
    }  
    //拼接批量跟新的sql语句
    public  String updateBatchSql(List<Object> list,String tableName,String idName){
    	if(list==null||list.size()<=0){
    		return null;
    	}
    	Set<Object> ids = new HashSet<Object>();
    	try{
    	//封装数据
    	Map<String, List<Map<Object, Object>>> sqlbuff=new HashedMap();
    	for (int i = 0; i < list.size(); i++) {
    		Field[] fields = list.get(i).getClass().getDeclaredFields();
			for (int j = 0; j < fields.length; j++) {
			//获取属性的值
			Object valueProperty = mybatisSqlUtils.readField(fields[j], list.get(i));
			//获取属性名
			String name=fields[j].getName();
			if(valueProperty!=null&&!name.equals(idName)){
				//先从sqlbuff中拿数据
				List<Map<Object, Object>> oldSql=sqlbuff.get(name);
				if(oldSql==null||oldSql.size()<=0){
					List<Map<Object, Object>> tmpSqlBuff=new ArrayList<Map<Object,Object>>();
					Map<Object, Object> tmpSqlMap=new HashedMap();
					//获取Id的值
					Object valueId= mybatisSqlUtils.readField(list.get(i).getClass().getDeclaredField(idName),list.get(i));
					//存储数据
					ids.add(valueId);
					tmpSqlMap.put(valueId, valueProperty);
					tmpSqlBuff.add(tmpSqlMap);
					sqlbuff.put(name, tmpSqlBuff);
				}else{
					Object valueId= mybatisSqlUtils.readField(list.get(i).getClass().getDeclaredField(idName),list.get(i));
					oldSql.get(0).put(valueId, valueProperty);
					ids.add(valueId);
				}
			}
			}
		}
    	//开始封装sql语句
    	StringBuffer sql = new StringBuffer("update ");
    	sql.append(" "+tableName+" set ");
    	for (String keytmp:sqlbuff.keySet()) {
    		sql.append(" "+keytmp+" = CASE "+idName+" ");
    		int s=0;
			for (Object id: sqlbuff.get(keytmp).get(0).keySet()) {
				if(sqlbuff.get(keytmp).get(0).get(id) instanceof String){
					sql.append(" WHEN "+id+" THEN '"+sqlbuff.get(keytmp).get(0).get(id)+"'");	
				}else{
					sql.append(" WHEN "+id+" THEN "+sqlbuff.get(keytmp).get(0).get(id)+" ");	
				}
				if(s==sqlbuff.get(keytmp).get(0).size()-1){
					sql.append(" end,");
				}
				s++;
			}
		}
    	
    	sql.deleteCharAt(sql.length()-1);
    	sql.append(" where "+idName+" IN(");
    	Iterator<Object> iterator = ids.iterator();
    	while (iterator.hasNext()) {
    		sql.append(iterator.next()+",");
    		//System.out.println(iterator.next());
		}
    	sql.deleteCharAt(sql.length()-1);
    	sql.append(")");
    	return sql.toString();
    	}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    /** 
     * 创建跟新语句 
     * */  
    public String updateOneByObj(Object target,String tableName,String idName) { 
    	try{
    	StringBuffer sql = new StringBuffer();
    	sql.append("update "+tableName +" set ");
    	Field[] fields = target.getClass().getDeclaredFields();
    	Object idValue= mybatisSqlUtils.readField(target.getClass().getDeclaredField(idName),target);
    	for (int i = 0; i < fields.length; i++) {
    		if(!fields[i].getName().equals(idName)){
    			Object valueProperty = mybatisSqlUtils.readField(fields[i], target);
    			if(valueProperty!=null){
    			if(valueProperty instanceof String){
    				sql.append(" "+fields[i].getName()+"='"+valueProperty+"',");
    			}else{
    				sql.append(" "+fields[i].getName()+"="+valueProperty+",");
    			}
    			}
    			
    		}
		}
    	sql.deleteCharAt(sql.length()-1);
    	sql.append(" where " +idName+"="+idValue);
    	return sql.toString();
    	}catch (Exception e) {
    		e.printStackTrace();
    		return null;
			
		}
    }  
  
}
