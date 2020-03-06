package test;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import util.DataBaseUtils;
import bean.User;
public class DBConnect {
	public static void main(String args[]) throws SQLException{
//		DataBaseUtils.config("jdbc.properties");
//		Connection conn = DataBaseUtils.getConnection();
//		System.out.println(conn);
		
//		String id = UUID.randomUUID() + "";
//		String createTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//		DataBaseUtils.update("INSERT INTO t_user(id,username,password,sex,create_time,is_delete,address,telephone) "
//				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",id,"lisi",345678,3,createTime,1,"保密","保密");
		
//		Map list = DataBaseUtils.queryForMap("select * from t_user where username = ?","zhangshan");
//		System.out.println(list);
		User user = DataBaseUtils.queryForBean("select * from t_user limit 1", User.class);
		System.out.println(user);
	}
}
