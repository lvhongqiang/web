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
<script type="text/javascript" src="js/module/order.js"></script>
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
<div ng-controller="order"> 
<ion-content>  
<ion-list class="ui-list ui-list-text">
			<ion-item class="ui-border-t" ng-repeat="g in list">
					<span style="float:left;line-height:15px;">{{$index}}.</span><a href="order_detail.jsp?orderId={{g.id}}" style="float:left;"><h3>{{g.title}}</h3>
						<p>{{g.postTime | date:'MM月dd日 HH:mm:ss'}}  总价:{{g.money}}<small>(元)</small>  净赚:{{g.money-g.costs}}<small>(元)</small></p>
					</a>
			<ion-option-button class="button button-assertive" on-tap="confirmDelete(expense.id)"> 
				Delete</ion-option-button>
			</ion-item>
            <ion-infinite-scroll ng-if="page.totalPage!=page.pageNo" on-infinite="doRefresh()" distance="100%" ></ion-infinite-scroll>
</ion-list>
</ion-content> 
</div>	
</body>
</html>
