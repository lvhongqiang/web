<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/tag.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<%@ include file="/inc/head.jsp"%>
	<%@ include file="/inc/topper.jsp"%>
	<c:if test="${source==1 }">${article.html }</c:if>
	<c:if test="${source==0 }">${article.content }</c:if>
	<%@ include file="/inc/footer.jsp"%>
</html>
