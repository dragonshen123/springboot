package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述 ： jdbc连接工具类<br/>
 * 创建人: 刘信朋  <br/>
 * 创建时间: 2017-8-23 下午06:10:56  <br/>
 */
public class JdbcUtil {
	/** 登录状态*/
	public boolean loginStatus = false;
	/**	连接*/
	public Connection conn = null;	   
	/** 登录错误信息*/
	public String error = null;        
	/** 数据库识别符（不同的数据库识别符不同）*/
	public String iden = "";
	/**	数据库类型*/
	public String type = "";		  
	/**	当前所查询的列名*/
	public List<String> columnNames =  new ArrayList<String>();
	/**
	 * 方法描述: 关闭数据库的连接 
	 * @param jdbc 要关闭连接的jdbc
	 */
	public static void close(JdbcUtil jdbc){
		try {
	        jdbc.conn.close();
        } catch (Exception e) {
        	System.out.println(e);
        }
	}
	
	/**
	 * 方法描述: 登录mysql数据库 
	 * @param ip IP地址
	 * @param port 端口号
	 * @param dbname 数据库名
	 * @param user 用户名
	 * @param pwd 密码
	 * @return 是否连接成功
	 */
	public boolean loginMySQL(String ip, int port, String dbname, String user, String pwd) {
		try {
			setType("Mysql");
			Class.forName("com.mysql.jdbc.Driver");
			this.conn = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dbname + "?characterEncoding=UTF8", user, pwd);
			loginStatus = true;
			error=null;
		} catch (Exception e) {
			e.printStackTrace();
			loginStatus = false;
			error = "MySQL登录异常。原因:" + e.getMessage();
		}
		return loginStatus;
	}

	/**
	 * 方法描述: 登录pgsql数据库
	 * @param ip IP地址
	 * @param port 端口号
	 * @param dbname 数据库名
	 * @param user 用户名
	 * @param pwd 密码
	 * @return 是否连接成功
	 */
	public boolean loginPgSQL(String ip, int port, String dbName, String user, String pwd) {
		try {
			setType("Pgsql");
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://" + ip + ":" + port + "/" + dbName;
			this.conn = DriverManager.getConnection(url, user, pwd);
			loginStatus = true;
			error=null;
		} catch (Exception e) {
			e.printStackTrace();
			loginStatus = false;
			error = "PgSQL登录异常。原因:" + e.getMessage();
		}
		return loginStatus;
	}

	/**
	 * 方法描述: 登录HANA数据库
	 * @param ip IP地址
	 * @param port 端口号
	 * @param dbname 数据库名
	 * @param user 用户名
	 * @param pwd 密码
	 * @return 是否连接成功
	 */
	public boolean loginHANA(String ip, int port, String dbName, String user, String pwd) {
		try {
			setType("HANA");
			Class.forName("com.sap.db.jdbc.Driver");
			String url = "jdbc:sap://" + ip + ":" + port + "/" + dbName + "?reconnect=true";
			this.conn = DriverManager.getConnection(url, user, pwd);
			loginStatus = true;
			error=null;
		} catch (Exception e) {
			e.printStackTrace();
			loginStatus = false;
			error = "PgSQL登录异常。原因:" + e.getMessage();
		}
		return loginStatus;
	}

	/**
	 * 方法描述: 登录Oracle数据库
	 * @param ip IP地址
	 * @param port 端口号
	 * @param dbname 服务名或sid
	 * @param user 用户名
	 * @param pwd 密码
	 * @return 是否连接成功
	 */
	public boolean loginOracle(String ip, int port, String dbname, String username, String password) {			
			try {
				setType("Oracle");
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String conStr = "jdbc:or1 acle:thin:@//" + ip + ":" + port + "/" + dbname;
				this.conn = DriverManager.getConnection(conStr, username, password);
				loginStatus = true;
				error=null;
			} catch (Exception e) {
				try {
					String conStr = "jdbc:oracle:thin:@" + ip + ":" + port + ":" + dbname;
					this.conn = DriverManager.getConnection(conStr, username, password);
					loginStatus = true;
					error=null;
                } catch (Exception e2) {
                	System.out.println(e2);
                	error = e.getMessage();
    				this.conn = null;
    				loginStatus=false;
    				if (error.indexOf("ORA-01017:") > -1) {
    					error = "Oracle登录失败。请检查提供的用户名和密码是否正确";
    				} else if (error.indexOf("ORA-12514:") > -1) {
    					error = "Oracle登录失败。监听程序当前无法识别连接描述符中请求的服务";
    				} else {
    					error = "登录失败。原因:" + e.getMessage();
    				} 
                }
		}
		return loginStatus;
	}

	/**
	 * 方法描述: 查询多条记录
	 * @param sql 查询语句
	 * @param params 参数
	 * @return 结果的集合
	 */
	

	/**
	 * 方法描述: 获取列名并通过iden连接起来（用于增加） 
	 * @param getColumn 
	 * @return 连接后的字符串
	 */
	public String getColumn(Object getColumn) {
		if (iden.length() == 2) {
			return iden.charAt(0) + getColumn.toString() + iden.charAt(1);
		}
		return getColumn.toString();
	}

	/**
	 * 方法描述: 获取参数并连接（用于增加） 
	 * @param value
	 * @return 连接后的字符串
	 */
	public String getValue(Object value) {
		if (value == null) {
			return "NULL";
		} 
		return "'" + value.toString().replaceAll("'", "''").replace("\\", "\\\\") + "'";
	}

	/**
	 * 方法描述:设置数据库的类型，指定数据库识别符 
	 * @param type 数据库类型
	 */
	public void setType(String type) {
		if (type.toLowerCase().equals("mysql")) {//可以使用
			this.type="MySQL";
			this.iden = "``";
		} else if (type.toLowerCase().equals("sqlserver")) {
			this.type="SqlServer";
			this.iden = "[]";
		} else if (type.toLowerCase().equals("oracle")) {//可以使用
			this.type = "Oracle";
			this.iden = "\"\"";
		} else if (type.toLowerCase().equals("hana")) {
			this.type = "HANA";
		} else if (type.toLowerCase().equals("pgsql")) {//可以使用
			this.type = "PgSQL";
			this.iden = "\"\"";
		}
	}


	/**
	 * 方法描述: 通过sql增加
	 * @param saveSql 要执行的保存语句
	 * @return 是否保存成功
	 */
	public boolean save(String saveSql) {
		int num = 0;
		try {
			PreparedStatement statement = conn.prepareStatement(saveSql);
			statement.executeUpdate();
			num++;
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return num == 1 ? true : false;
	}

	
	/**
	 * 方法描述:通过sql语句修改 
	 * @param sql 要执行的修改语句
	 * @param params 参数
	 * @return 修改成功的条数
	 */
	public int update(String sql, Object... params) {
		int num = 0;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			if (params != null && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					statement.setObject(i + 1, params[i]);
				}
			}
			num = statement.executeUpdate();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return num;
	}


	/**
	 * 方法描述: 删除，需要指定主键，主键的值 
	 * @param tableName 表名
	 * @param primaryKey 主键
	 * @param id 要删除记录的主键值
	 * @return 是否删除成功
	 */
	public boolean delete(String tableName, String primaryKey, Object id) {
		int num = 0;
		try {
			String modelName="";
			if(type.equals("PgSQL")){//pgsql有模式名，必须指定模式名 把模式名通过表传过来
				String[] names = tableName.split("\\.");
				modelName= names[0]+".";
				tableName = names[1];
			}
			String sql = "DELETE FROM " +modelName+ getColumn(tableName);
			sql += " WHERE " + getColumn(primaryKey) + "=" + getValue(id);
			PreparedStatement statement = conn.prepareStatement(sql);
			num = statement.executeUpdate();
			statement.close();
			return num == 1 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 方法描述: 测试
	 * @param args
	 */
	public static void main(String[] args) {
//	//1.测试查询,修改pgsql
//		JdbcDao jdbc = new JdbcDao();
//		//增加
//		jdbc.loginPgSQL("localhost", 5432, "postgres", "postgres", "root123");
//		Record rr =  new Record().set("id",1).set("name","lxp");
//		jdbc.save("pub.blog",rr);
//		//查询
//		Record r = jdbc.findFirst("select * from pub.\"blog\" where id=?", rr.get("id"));
//		System.out.println(r);
//		r.set("password","root1234");
//		//修改
//		jdbc.update("pub.blog", "id", r);
//		System.out.println(r);		
//		System.out.println(jdbc.delete("pub.blog", "id", r.get("id")));
//	//2隐藏 查询字段
//		System.out.println(jdbc.columnNames);
//		System.out.println(new JdbcDao().loginMySQL("localhost", 3306, "jfinal", "root", "root123"));
		JdbcUtil jdbc = new JdbcUtil();
		System.out.println(jdbc.loginOracle("localhost", 1521, "orcl", "system", "root123"));
		
	}
}
