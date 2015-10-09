<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/tag.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html lang="en" ng-app="baking">
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta charset="UTF-8">
<%@ include file="/inc/src.jsp"%>
<script type="text/javascript" src="ionic-v1.1.0/js/ionic.bundle.js"></script>
<script type="text/javascript" src="js/module/make.js"></script>
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
</style>
  </head>
  
  <body>
<div ng-controller="makeOrder"  style="height:100%">
		<ul class="ui-list ui-list-text">
			<li class="ui-border-t" ng-repeat="g in goods">
				<div class="ui-list-info">
					<h5>{{g.name}}</h5>
				</div>
				<button class="ui-btn-s ui-btn-danger" ng-click="subtract(g.id)">-</button>
				<div class="ui-input ui-border-radius" style="width:25px;">
					<input type="number" name="" value="{{orders[g.id]}}" size="2" placeholder="0">
				</div>
				<button class="ui-btn-s ui-btn-danger" ng-click="add(g.id)">+</button>
			</li>
		</ul>
		<ul class="ui-list ui-list-pure ui-border-t">
		<li class="ui-border-t">
	<div class="ui-btn-wrap">
				<button class="ui-btn-lg ui-btn-primary" ng-click="save()">确定</button>
			</div>
		</li>
	</ul>
</div>
</body>
</html>
