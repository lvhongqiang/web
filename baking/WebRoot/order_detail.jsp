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
<script type="text/javascript" src="js/module/order_detail.js"></script>
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
body,ul{
	overflow:auto;
	-webkit-overflow-scrolling: touch;
}
</style>
  </head>
  
  <body ng-controller="orderDetail" ng-init="orderId=<%=orderId %>;getlist();">
   	<div class="ui-list" style="padding:15px;">
   		<h6>订单信息：</h6>
		<h3>{{order.title}}</h3>订单日期:{{order.postTime | date:'MM月dd日 HH:mm:ss'}}  总价:{{order.money}}<small>(元)</small>  净赚:{{order.money-order.costs}}<small>(元)</small>
   	</div>
   	<div class="tabs" style="position:relative;">
	  <a class="tab-item" ng-click="showUl='details'">
	    备料详细：
	  </a>
	  <a class="tab-item" ng-click="showUl='costs'">
	    库存消耗：
	  </a>
	</div>
   	<ul class="ui-list ui-list-pure ui-border-tb" ng-show="showUl=='details'">
		<li class="ui-border-t" ng-repeat="g in list">
			<h3 ng-if="list.length>1">第{{$index+1}}组: {{g.title}}</h3>
			<p>
			<ul  class="ui-list ui-list-pure ui-border-tb">
				<li class="ui-border-t" ng-repeat="s in g.steplist">
						<h4>step{{$index+1}}:{{s.step.stepName}}</h4>
					<ul class="ui-list ui-list-pure ui-border-tb">
						<li class="ui-border-t" ng-repeat="p in s.list">
								<h5 ng-if="s.list.length>1">第{{$index+1}}锅：{{p.title}}</h5>
							<p ng-repeat="r in p.recipeList">{{r.inventory.name}}:  {{r.usage}} {{r.inventory.unit}}</p>
						</li>
					</ul>
				</li>
			</ul>
			</p>
		</li>
	</ul>
		
	<ul class="ui-list ui-list-pure ui-border-tb" ng-init="getcostlist();" ng-show="showUl=='costs'">
		<li class="ui-border-t" style="padding:20px;">
				<h4>原料：</h4>
				<p ng-repeat="g in costlist | filter : {type : 1}">{{g[0].name}}   {{g[1]}} {{g[0].unit}}</p>
		</li>
		<li class="ui-border-t" style="padding:20px;">
				<h4>包装：</h4>
				<p ng-repeat="g in costlist | filter : {type : 2}">{{g[0].name}}   {{g[1]}} {{g[0].unit}}</p>
		</li>
		<li class="ui-border-t" style="padding:20px;">
			<div class="row">
			  <div class="col"><button class="ui-btn-lg ui-btn-danger" ng-click="save()">减去相应库存</button></div>
			</div>
			<div class="row">
			  <div class="col col-75"><h3 style="color:#aaa;">已减去相应库存</h3></div>
			  <div class="col"><button class="ui-btn ui-btn-danger" ng-click="save()">还原</button></div>
			</div>
		</li>
	</ul>	
</body>
</html>
