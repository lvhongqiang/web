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
  
  <body >
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
            <div id="editor-md">
                <textarea style="display:none;">${blog.markdown }</textarea>
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
        <script src="editor.md/editormd.min.js"></script>
	    
        <script type="text/javascript">
			var mdEditor;
	        var ue;
            $(function() {                
                mdEditor = editormd({
                    id      : "editor-md",
                    width   : "90%",
                    height  : 640,
                    path    : "editor.md/lib/",
                    saveHTMLToTextarea : true    // 保存HTML到Textarea

                });
                $("form").submit(function() {
                   $("#blog_markdown").val(mdEditor.getMarkdown());
                   $("#blog_html").val(mdEditor.getHTML());
                });  
            });
            
        </script>
   


  </body>
</html>
