<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/tag.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String orderId=request.getParameter("orderId");


%>

<!doctype html>
<html lang="en" ng-app="baking">
  <head>
    
    <title>My JSP 'index.jsp' starting page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta charset="UTF-8">
<%@ include file="/inc/src.jsp"%>
<script type="text/javascript" src="ionic-v1.1.0/js/ionic.bundle.js"></script>
<script type="text/javascript" src="js/module/order_costs.js"></script>
<link href="ionic-v1.1.0/css/ionic.css" rel="stylesheet">

<style>
.ui-btn-s {
    font-size: 25px;
    height: 25px;
    line-height: 25px;
    width: 35px;
}
.ui-list-info{
	width:150px;
}
body{
	overflow:auto;
	-webkit-overflow-scrolling: touch;
}
</style>
  </head>
  
  <body>
<div ng-controller="orderCosts" > 
		<ul class="ui-list ui-list-text" ng-init="orderId=<%=orderId %>;getlist();">
			<li class="ui-border-t" ng-repeat="g in list">
				<div class="ui-list-info">
					<h5>{{g[0].name}}</h5>
				</div>
				<div>{{g[1]}}{{g[0].unit}}</div>
			</li>
		</ul>
</div>	
</body>
</html>
