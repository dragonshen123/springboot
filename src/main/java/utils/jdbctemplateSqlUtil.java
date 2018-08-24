package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Vector;

import javax.persistence.Table;

import org.apache.commons.lang.reflect.FieldUtils;

public  class jdbctemplateSqlUtil {  
    private Object target ;   
      
    private String idName ;   
      
    private Object idValue ;   
      
    private SqlType currentType ;   
    private List<Object> param = new Vector<Object>();  
    
    public enum SqlType {  
        INSERT, UPDATE, DELETE   
    }  
    
  
     
      
    /** 
     * 创建删除 
     * */  
    public  String createDelete(Object target,String tableName,String idName) {   
    	try{
        getFields( target.getClass() );  
        StringBuffer sqlBuffer = new StringBuffer() ;   
        sqlBuffer.append("DELETE FROM ").append(tableName).append(" WHERE ") ;  
        for(Field field : fields){  
            if(!Modifier.isStatic(field.getModifiers())){  
            	if(idName.equals(field.getName())){
                    sqlBuffer.append( field.getName()).append("=?");  
                    param.add(readField(field,target)); 
                    idName = field.getName() ;   
                    idValue = readField(field,target)   ;   
            	}
            }  
            
        }  
        if(idName == null){  
            throw new RuntimeException( "id字段不正确" + target.getClass() + "'s ID") ;   
        } 
        return sqlBuffer.toString();  
    	}catch (Exception e) {
    		e.printStackTrace();
			// TODO: handle exception
    		return null;
		}
    }  
      
     //获取字段的值
    private Object readField(Field field,Object  target){  
        try {  
            return FieldUtils.readField( field , target, true )  ;   
        } catch (Exception e) {  
            e.printStackTrace();
            return null;
        }  
    }  
      
    /** 
     * 创建跟新语句 
     * */  
    public String createUpdate(Object target,String tableName,String idName) { 
    	try{
        getFields( target.getClass() );  
        String sql ="UPDATE "+tableName+" SET ";
           int notNullLength=0;
        for(int j=0;j<fields.size();j++){
        	Field field=fields.get(j);
        	if(!Modifier.isStatic(field.getModifiers())&&readField(field,target)!=null){
        		notNullLength++;
        	}
        }
        
        if(notNullLength==0){
        	return null;
        }
        System.out.println("长度："+notNullLength);
        int flag=0;
            for(int i=0;i<fields.size();i++){ 
            	Field field=fields.get(i);
                if(!Modifier.isStatic(field.getModifiers())&&readField(field,target)!=null){  
               
                    if(!idName.toLowerCase().equals(field.getName().toLowerCase())){ 
                    	flag++;
                    	if(flag==notNullLength-1){
                    		sql+=field.getName()+"=?";
                    	}else{
                    		sql+=field.getName()+"=?,";
                    	
                    	}
                        param.add( readField(field,target) ) ; 
                        
                    }else{  
                        idName = field.getName() ;   
                        idValue = readField(field,target)   ;   
                    }  
                }  
            }  
            System.out.println("长度："+flag);
            if(idName == null){  
                throw new RuntimeException( "id字段不正确" + target.getClass() + "'s ID") ;   
            }  
            sql+=" WHERE "+idName+"=?"; 
            param.add( idValue );  
           String newSql= sql;
           sql="";
          return  newSql ;  
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		return null;
		}
    }  
  
    /** 
     * 根据注解获取表名 
     * */  
    private String getTableName() {  
        String tableName = null ;  
        Class<?> clazz = target.getClass() ;  
        tableName = getTableNameForClass(clazz);  
        return tableName ;  
    }  
  
    private String getTableNameForClass(Class<?> clazz) {  
        String tableName;  
        Table table = clazz.getAnnotation(Table.class) ;   
        if(null != table){  
            tableName = table.name() ;  
            if("".equalsIgnoreCase(tableName)){  
                tableName = clazz.getSimpleName() ;  
            }  
        }else{  
            tableName = clazz.getSimpleName() ;  
        }  
        return tableName;  
    }  
      
    /** 
     * 创建插入语句 
     * */  
    public String createInsert(Object target,String tableName) { 
    	try{
        getFields( target.getClass() );  
        StringBuffer sqlBuffer = new StringBuffer() ;   
        sqlBuffer.append("INSERT INTO ").append(tableName)  
        .append("(");  
           int flag=0;
            for(Field field : fields){  
                if(!Modifier.isStatic(field.getModifiers())&&readField(field,target)!=null){  
                        sqlBuffer.append( field.getName()).append(",") ;   
                        param.add( readField(field,target) ) ;  
                        flag++;
                }  
            }  
           if(flag==0){
        	  return null; 
           }
            int length = sqlBuffer.length() ;  
            sqlBuffer.delete(length-1, length).append(")values(");   
            int size = param.size() ;  
            for(int x=0;x<size;x++){  
                if(x != 0){  
                    sqlBuffer.append(",") ;    
                }  
                sqlBuffer.append("?") ;   
            }  
            sqlBuffer.append(")");  
        return sqlBuffer.toString() ;  
    	}catch (Exception e) {
    		e.printStackTrace();
			// TODO: handle exception
    		return null;
		}
    }  
      
  public String getSelectPageSql(Object target,String tableName){
	  try{   if(target!=null){
	        getFields( target.getClass() ); 
	        System.out.println("查询字段"+target.toString());
	          }
	  
	        StringBuffer sqlBuffer = new StringBuffer() ;   
	        sqlBuffer.append("select * from ").append(tableName)  
	        .append(" where 1=1 ");  
	         
	            for(Field field : fields){  
	                if(!Modifier.isStatic(field.getModifiers())&&readField(field,target)!=null){  
	                        sqlBuffer.append( " and "+field.getName()).append(" like '%"+readField(field,target)+"%' ") ;   
	                }  
	            }   
	        return sqlBuffer.toString() ;  
	    	}catch (Exception e) {
	    		e.printStackTrace();
				// TODO: handle exception
	    		return null;
			}
  }
    public List<Object> getParam() {  
        return param;  
    }  
  
    
      
    public String getIdName() {  
        return idName;  
    }  
  
    public Object getIdValue() {  
        return idValue;  
    }  
    List<Field> fields = new Vector<Field>() ;   
      
    private void getFields(Class<?> clazz){  
        if(Object.class.equals(clazz)){  
            return ;  
        }  
        Field[]fieldArray = clazz.getDeclaredFields()  ;  
        for(Field file : fieldArray){  
            fields.add( file );  
        }  
        getFields(clazz.getSuperclass());   
    }  
      
    
}  