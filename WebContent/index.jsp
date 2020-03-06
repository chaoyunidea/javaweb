<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
*{
    padding: 0 ;
    margin: 0 ;
    font-family: "微软雅黑" ;
}
ul li {
    list-style: none ;
}
.header ul{
	display: inline-block;
    vertical-align: top;
}
.header ul li {
    float: left ;
}
a {
    color: #fff ;
    text-decoration: none ;
}
.header {
    height: 72px ;
    background: #458fce ;
}
.header ul li :hover {
    background:#74b0e2 ;
}
.header .logo {
    color: #fff ;
    line-height: 72px ;
    font-size: 30px ;
    margin-left: 20px ;
    display:inline-block ;
    font-weight:500 ;
}
.header ul li.first {
    margin-left: 30px ;
}
 
.header ul li {
    float: left ;
    color: #fff ;
    display: inline-block ;
    width: 112px ;
    height: 72px ; 
    text-align: center ;
    line-height:72px ;
    cursor: pointer ;
}

.header .login {
    float: right ; 
    color: #fff ;
    line-height: 72px ;
    margin-right: 20px ;
}
</style>
</head>
<body>
	<!-- 头部页面 -->
	<%@include file="common/header.jsp" %>
</body>
</html>