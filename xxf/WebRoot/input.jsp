<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/tag.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>页面转换</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<style>
a{line-height:30px;height:100px;font-size:30px;}
div{width:300px;margin:100px auto;text-align:center;verticle-align:middle;}
</style>
  </head>
  
  <body>
  	<div>
	<a href="artsc${aid}.html">转换后页面</a><br/><br/><br/>
	<a href="art${aid}.html">备用页面</a><br/><br/><br/>
	<a href="">编辑页面</a>
	</div>
  </body>
</html>
