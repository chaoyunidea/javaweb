package service;

import bean.User;
import util.DataBaseUtils;

public class LoginService {
	public User getUser(String username){
		String sql = "select * from t_user where username = ?";
		User user = DataBaseUtils.queryForBean(sql, User.class, username);
		if(user == null){
			return null;
		}
		return user;
	}
}
