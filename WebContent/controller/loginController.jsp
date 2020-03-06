<%@ page language="java" import="java.util.*,service.LoginService,util.StringUtils,bean.*" pageEncoding="UTF-8"%>
<%

    //设置请求的编码
    //request.setCharacterEncoding("UTF-8");
    //获取客户端传递过来参数
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    
    //如果用户名和密码不为空
    if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
    	out.print(-1);
    }else{
    	LoginService loginService = new LoginService();
    	
    	User user = loginService.getUser(username);
    	if(user == null){
    		out.print("-2");
    	}else{
    		if(!username.equals(user.getUsername()) || !password.equals(user.getPassword())){
    			out.print("-3");
    		}else{
    			out.print("1");
    			session.setAttribute("user",user);
    			session.setAttribute("username",user.getUsername());
    		}
    	}
    }
%>