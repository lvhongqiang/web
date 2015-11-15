<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
	<head>
    <base href="<%=basePath%>">
		<title>哈喽吕小强-的技术博</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="web工程师的技术博客,java,web,网站,app,android,ios,博客,干货,网站开发,手机app" />
		<meta name="keywords" content="java,web,网站,app,android,ios,博客,干货,网站开发,手机app" />
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
	<!--
		Note: Set the body element's class to "left-sidebar" to position the sidebar on the left.
		Set it to "right-sidebar" to, you guessed it, position it on the right.
	-->
	<body class="left-sidebar">

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Content -->
					<div id="content">
						<div class="inner">
						<s:iterator value="page.list" var="b">
							<!-- Post -->
								<article class="box post post-excerpt">
									<header>
										<h2><a href="blog${b.id}.html">${b.title }</a></h2>
										<p></p>
									</header>
									<div class="info">
										<s:set value="TimeFormat(#b.createTime)" var="t"/>
										<span class="date"><span class="month">${t.mon}<span>${t.th }</span></span> <span class="day">${t.day }</span><span class="year">, ${t.year }</span></span>
									<!-- <ul class="stats">
											<li><a href="" class="icon fa-comment">16</a></li>
											<li><a href="" class="icon fa-heart">32</a></li>
											<li><a href="" class="icon fa-twitter">64</a></li>
											<li><a href="" class="icon fa-facebook">128</a></li>
										</ul> -->	
									</div>
									<!-- <a href="" class="image featured"><img src="images/pic02.jpg" alt="" /></a> -->
									<p>
										${b.brief }
									</p>
								</article>
						</s:iterator>
							<!-- Pagination -->
								<div class="pagination">
									<s:if test="page.pageNo>1"><a href="blogs${page.prePage}.html" class="button previous">Previous Page</a></s:if>
									<div class="pages">
									
									<s:if test="page.start>2">
										<a href="blogs.html">1</a>
										<span>&hellip;</span>
									</s:if>
									<s:iterator value="page.range" var="p">
										<a href="blogs${p}.html"<s:if test="#p==page.pageNo"> class="active"</s:if>>${p }</a>
									</s:iterator>
									<s:if test="page.end<page.totalPage-1">
										<span>&hellip;</span>
										<a href="blogs${page.totalPage}.html">${page.totalPage }</a>
									</s:if>
									</div>
									<s:if test="page.pageNo<page.totalPage">
									<a href="blogs${page.nextPage}.html" class="button next">Next Page</a>
									</s:if>
								</div>

						</div>
					</div>

				<!-- Sidebar -->
					<%@include file="sidebar.jsp" %>

			</div>

	</body>
</html>