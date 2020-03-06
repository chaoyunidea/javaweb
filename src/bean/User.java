package bean;

import annotation.Column;
import annotation.Table;

@Table(tableName = "t_user")
public class User {
	@Column(type = "varchar(100)" ,field = "id" ,primaryKey = true ,defaultNull = false)
	private String id;        //主键，采用UUID

	@Column(type = "VARCHAR(20)", field = "username")
	private String username;  //用户名

	@Column(type = "VARCHAR(20)", field = "password")
	private String password;  //密码

	@Column(type = "VARCHAR(60)", field = "headerPic")
	private String headerPic; //头像

	@Column(type = "VARCHAR(60)", field = "email")
	private String email;     //电子邮箱

	@Column(type = "VARCHAR(2)", field = "sex")
	private String sex;      //性别 0男 1女 3保密

	@Column(type = "datetime", field = "create_time")
	private String createTime;//创建时间

	@Column(type = "timestamp", field = "update_time")
	private String updateTime;//最后更新时间

	@Column(type = "int(1)", field = "is_delete")
	private Integer isDelete; // 删除状态  0未删除  1删除

	@Column(type = "VARCHAR(200)", field = "address")
	private String address;   //地址

	@Column(type = "VARCHAR(15)", field = "telephone")
	private String telephone; //电话
	
	public void setId(String id) {
        this.id = id;
    }
	
	public void setUsername(String username) {
        this.username = username;
    }
	
	public void setPassword(String password) {
        this.password = password;
    }
	public void setHeaderPic(String headerPic) {
        this.headerPic = headerPic;
    }
	public void setEmail(String email) {
        this.email = email;
    }
	public void setSex(String sex) {
        this.sex = sex;
    }
	
	public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
	
	public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
	
	public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
	
	public void setAddress(String address) {
        this.address = address;
    }
	public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
	
	public String getUsername(){
		return this.username;
	}
	
	public String getPassword(){
		return this.password;
	}
}
