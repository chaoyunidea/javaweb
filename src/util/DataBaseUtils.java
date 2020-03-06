package util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.mysql.jdbc.PreparedStatement;


import test.TestProperties;

public class DataBaseUtils {
	
	private static String username;
	private static String password;
	private static String dataBaseName;
	
	/**
	 * 配置数据库的基本信息
	 * @return void
	 */
	public static void config(String path){
		InputStream inputStream = TestProperties.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties p = new Properties();
		try{
			p.load(inputStream);
			username = p.getProperty("db.username");
			password = p.getProperty("db.password");
			dataBaseName = p.getProperty("db.dataBaseName");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	static{
		config("jdbc.properties");
	}
	
	/**
	 * 获取数据库链接
	 * @return Connection 
	 */
	public static Connection getConnection(){
		Connection connection = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
			+ dataBaseName+"?useUnicode=true&characterEncoding=utf8",username,password);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return connection;
	}
	
	/**
	 * 关闭资源
	 * @param connection
	 * @param statement
	 * @param rs
	 */
	public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet rs){
		try {
			if(rs!=null) rs.close();
			if(statement!=null) statement.close();
			if(connection!=null)connection.close();
		}catch(Exception e){
			e.fillInStackTrace();
		}
	}
	
	/**
	 * DML操作
	 * @param sql
	 * @param objects
	 */
	public static void update(String sql,Object...objects){
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try{
			statement = (PreparedStatement) connection.prepareStatement(sql);
			for(int i=0;i<objects.length;i++){
				statement.setObject(i+1, objects[i]);
			}
			statement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeConnection(connection,statement,null);
		}
	}
	
	/**
	 * 查询出数据，并且list返回
	 * @param sql
	 * @param objects
	 * @return
	 * @throws SQLException
	 */
	public static List<Map<String,Object>> queryForList(String sql,Object...objects){
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try{
			statement = (PreparedStatement) connection.prepareStatement(sql);
			for(int i=0;i<objects.length;i++){
				statement.setObject(i+1, objects[i]);
			}
			rs = statement.executeQuery();
			while(rs.next()){
				ResultSetMetaData resultSetMetaData = rs.getMetaData();
				int count = resultSetMetaData.getColumnCount();
				Map<String,Object> map = new HashMap<String,Object>();
				for(int i=0;i<count;i++){
					map.put(resultSetMetaData.getColumnName(i+1),
							rs.getObject(resultSetMetaData.getColumnName(i+1)));
				}
				result.add(map);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeConnection(connection,statement,rs);
		}
		
		return result;
	}
	
	/**
	 * 查询出数据，并且map返回
	 * @param sql
	 * @param objects
	 * @return
	 * @throws SQLException
	 */
	public static Map<String,Object> queryForMap(String sql,Object...objects) throws SQLException{
		Map<String,Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = queryForList(sql,objects);
		if(list.size() != 1){
			return null;
		}
		result = list.get(0);
		return result;
	}
	
	/**
	 * 查询出数据，并且返回一个JavaBean
	 * @param sql
	 * @param clazz
	 * @param objects
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	public static <T>T queryForBean(String sql,Class clazz,Object...objects){
		T obj = null;
		Map<String,Object> map = null;
		Field field = null;
		try{
			obj = (T) clazz.newInstance();
			map = queryForMap(sql,objects);
		}catch(InstantiationException | IllegalAccessException e){
			e.printStackTrace();		
		}catch(SQLException e){
			e.printStackTrace();
		}
		if(map == null){
			return null;
		}
		
		for(String columnName:map.keySet()){
			Method method = null;
			String propertyName = StringUtils.columnToProperty(columnName);
			
			try{
				field = clazz.getDeclaredField(propertyName);
				
			}catch(NoSuchFieldException e){
				e.printStackTrace();
			}catch(SecurityException e){
				e.printStackTrace();
			}
			
			String fieldType = field.toString().split(" ")[1];
			Object value = map.get(columnName);
			if(value == null){
				continue;
			}
			
			String setMethodName = "set" + StringUtils.upperCaseFirstCharacter(propertyName);
			
			try{
				String valueType = value.getClass().getName();
				
				if(!fieldType.equalsIgnoreCase(valueType)){
					if(fieldType.equalsIgnoreCase("java.lang.Integer")){
						value = Integer.parseInt(String.valueOf(value));
					}else if(fieldType.equalsIgnoreCase("java.lang.String")){
						value = String.valueOf(value);
					}else if(fieldType.equalsIgnoreCase("java.util.Date")){
						valueType = "java.util.Date";
						String dateStr = String.valueOf(value);
						Timestamp ts = Timestamp.valueOf(dateStr);
						Date date = new Date(ts.getTime());
						value = date;
					}
				}
				//System.out.println(Class.forName(fieldType));
//				field.setAccessible(true);
//				field.set(clazz,value);
				method = clazz.getDeclaredMethod(setMethodName, Class.forName(fieldType));
				method.invoke(obj, value);
			}catch(Exception e){
				e.printStackTrace();
			}			
		}		
		return obj;		
	}
	
}
