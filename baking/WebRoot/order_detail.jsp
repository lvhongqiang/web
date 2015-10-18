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
body{
	overflow:auto;
	-webkit-overflow-scrolling: touch;
}
</style>
  </head>
  
  <body>
<div ng-controller="orderDetail" > 
		<ul class="ui-list ui-list-pure ui-border-tb" ng-init="orderId=<%=orderId %>;getlist();">
			<li class="ui-border-t" >
			<h6>订单信息：</h6>
			<span style="font-size:140%; ">{{order.title}}</span> ({{order.postTime | date:'MM月dd日 HH:mm:ss'}})  总价:{{order.money}}<small>(元)</small>  净赚:{{order.money-order.costs}}<small>(元)</small>
			</li>
			<li class="ui-border-t" >
			<h6>备料详细列表：</h6>
			</li>
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
</div>	
</body>
</html>
