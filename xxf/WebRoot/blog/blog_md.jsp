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
		<title>哈喽吕小强-的技术博-${blog.title }</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="${blog.title },web工程师的技术博客,java,web,网站,app,android,ios,博客,干货,网站开发,手机app" />
		<meta name="keywords" content="${blog.title },java,web,网站,app,android,ios,博客,干货,网站开发,手机app" />
		<!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->
		<script src="js/jquery.min.js"></script>
		<script src="js/skel.min.js"></script>
		<script src="js/skel-layers.min.js"></script>
		<script src="js/init.js"></script>
			<link rel="stylesheet" href="editor.md/css/editormd.min.css" />
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
							<!-- Post -->
								<article class="box post post-excerpt">
									<header>
										<h2><a href="">${blog.title }</a></h2>
										<p></p>
									</header>
									<div class="info">
										<s:set value="TimeFormat(blog.createTime)" var="t"/>
										<span class="date"><span class="month">${t.mon}<span>${t.th }</span></span> <span class="day">${t.day }</span><span class="year">, ${t.year }</span></span>
									 <ul class="stats">
											<li><a href="" class="icon fa-comment">16</a></li>
											<li><a href="" class="icon fa-heart">32</a></li>
											<li><a href="" class="icon fa-twitter">64</a></li>
											<li><a href="" class="icon fa-facebook">128</a></li>
										</ul>
									</div>
									<!-- <a href="" class="image featured"><img src="images/pic02.jpg" alt="" /></a> -->
									
									<div id="test-editormd-view">
						                <textarea id="append-test" style="display:none;">${blog.markdown }</textarea>          
						            </div>
									
								</article>
							<!-- Pagination
								<div class="pagination">
									<!--<a href="" class="button previous">Previous Page</a>
									<div class="pages">
										<a href="" class="active">1</a>
										<a href="">2</a>
										<a href="">3</a>
										<a href="">4</a>
										<span>&hellip;</span>
										<a href="">20</a>
									</div>
									<a href="" class="button next">Next Page</a>
								</div>-->

						</div>
					</div>

				<!-- Sidebar -->
					<%@include file="sidebar.jsp" %>
			</div>
 <script src="editor.md/js/jquery.min.js"></script>       
        <script src="editor.md/lib/marked.min.js"></script>
        <script src="editor.md/lib/prettify.min.js"></script>
        
        <script src="editor.md/lib/raphael.min.js"></script>
        <script src="editor.md/lib/underscore.min.js"></script>
        <script src="editor.md/lib/sequence-diagram.min.js"></script>
        <script src="editor.md/lib/flowchart.min.js"></script>
        <script src="editor.md/lib/jquery.flowchart.min.js"></script>

       
 <script src="editor.md/editormd.min.js"></script>
<script>
$(function() {
                var testEditormdView;
                
                testEditormdView = editormd.markdownToHTML("test-editormd-view", {
                    htmlDecode      : true,  // "style,script,iframe",  //you can filter tags decode
                    emoji           : true,
                    taskList        : true,
                    tex             : true,  // 默认不解析
                    flowChart       : true,  // 默认不解析
                    sequenceDiagram : true,  // 默认不解析
                });
            });
</script>


<div style="display:none;">
	${blog.html }
</div>
	</body>
</html>