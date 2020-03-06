package test;

import bean.User;
import util.TableUtils;

public class testMain {
	 public static void main(String[] args) {
        String sql = TableUtils.getCreateTableSQL(User.class);
        System.out.println(sql);
    }
}
