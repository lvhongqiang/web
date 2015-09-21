<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->
		<script src="js/jquery.min.js"></script>
		<script src="js/skel.min.js"></script>
		<script src="js/skel-layers.min.js"></script>
		<script src="js/init.js"></script>
		<noscript>
			<link rel="stylesheet" href="css/skel.css" />
			<link rel="stylesheet" href="css/style.css" />
			<link rel="stylesheet" href="css/style-desktop.css" />
			<link rel="stylesheet" href="css/style-wide.css" />
		</noscript>
		<!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->
	<link rel="stylesheet" href="css/blog.css" />

  </head>
  
  <body>
  <div class="container">
    <div class="topper"></div>
    <div class="content">
    发布文章：<a href="blog/manageradd_md.action">Markdown</a> | <a href="blog/manageradd_html.action">html</a>
		<ol>
		<s:iterator value="page.list" var="b">
			<li class="blog_li">
				<div><a href="">${b.title }</a><span>
					<s:if test="#b.type==1">Markdown</s:if><s:else>Html</s:else></span>
					<span><s:date name="#b.createTime" format="yyyy-MM-dd HH:mm:ss"/></span>
					<span class="control"><a href="blog/managerupdate.action?id=${b.id }">修改</a> | <a href="blog/managerdel.action?id=${b.id }">删除</a></span></div>
				<div>${b.brief }</div>
			</li>
		</s:iterator>
		</ol>

		<!-- Pagination -->
						<div class="pagination">
							<s:if test="page.pageNo>1"><a href="blog/managerlist.action?p=${page.prePage}" class="button previous">Previous Page</a></s:if>
							<div class="pages">
							<s:iterator value="page.range" var="p">
								<a href="blog/managerlist.action?p=${p}"<s:if test="#p==page.pageNo"> class="active"</s:if>>${p }</a>
							</s:iterator>
							<s:if test="page.end<page.totalPage-1">
								<span>&hellip;</span>
								<a href="#">20</a>
							</s:if>
							</div>
							<s:if test="page.pageNo<page.totalPage">
							<a href="blog/managerlist.action?p=${page.nextPage}" class="button next">Next Page</a>
							</s:if>
						</div>

	</div>
    <div class="footer"></div>
  </div>
  </body>
</html>
