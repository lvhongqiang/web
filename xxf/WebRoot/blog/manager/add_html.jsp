<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html lang="en" ng-app="blogApp">
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'add.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
 	<meta charset="utf-8" />
	<link rel="stylesheet" href="editor.md/css/style.css" />
    <link rel="stylesheet" href="editor.md/css/editormd.min.css" />
    <link rel="shortcut icon" href="https://pandao.github.io/editor.md/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="css/blog.css" />
	<script src="js/angular.min.js"></script>
	<script src="js/controllers.js"></script>
	<script type="text/javascript">
	
	</script>
  </head>
  
  <body>
	  <div class="topper"></div>
	  <form action="blog/managersave.action" method="post">
		  <input type="hidden" name="blog.id" value="${blog.id }"/>
		  <input type="hidden" name="blog.markdown" id="blog_markdown"/>
		  <input type="hidden" name="blog.html" id="blog_html"/>
		  <input type="hidden" name="blog.createTime" value="<s:date name='blog.createTime' format='yyyy-MM-dd HH:mm:ss'/>"/>
		  <input type="hidden" name="blog.type" value="${blog.type }" />
	       <div id="layout">
	       		<div class="hang">
		            <h2>正文：<button type="button" onclick="javascript:history.go(-1);" class="goback">返回</button>                <button type="submit" class="submit">保存</button></h2>
	       		</div>
	            <div id="editor-html" style="width: 90%; margin:0 auto; min-height: 640px;">
	                    <!-- 加载编辑器的容器 -->
					    <script id="container" name="content" type="text/plain" style="height:540px;">${blog.html}</script>
	            </div>
	            
	            <div class="hang">
	                <h2>标题：</h2>
	                <input type="text" name="blog.title" value="${blog.title }" style="width:99%;height:30px;font-size: 18px;padding-left:3px;"/>
	            </div>
	            <div class="hang">
	                <h2>简介：</h2>
	                <textarea  name="blog.brief" style="width:99%;height:120px;font-size: 13px;padding-left:3px;">${blog.brief }</textarea>
	            </div>
	        </div>
        </form>
        
        <script src="editor.md/js/jquery.min.js"></script>
	    <!-- 配置文件 -->
	    <script type="text/javascript" src="ueditor/ueditor.config.js"></script>
	    <!-- 编辑器源码文件 -->
        <script src="ueditor/ueditor.all.min.js"></script>
	    <!-- 实例化编辑器 -->
        <script type="text/javascript">
	        var ue;
            $(function() {             
                ue = UE.getEditor('container');
            });
            $("form").submit(function() {
                $("#blog_html").val(ue.getContent());
             });  
        </script>
   


  </body>
</html>
