<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/tag.jsp"%>


<!doctype html>
<html lang="en" ng-app="baking">
  <head>
    
    <title>My JSP 'index.jsp' starting page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta charset="UTF-8">
<%@ include file="/inc/src.jsp"%>
  </head>
  
  <body>
<div class="ui-flex ui-flex-align-center ui-flex-pack-center" style="height:100%">
<ul class="ui-list ui-list-pure">
    <li class="ui-border-t"><a href="make.jsp">
	<div class="ui-btn-wrap">
	    <button class="ui-btn-lg ui-btn-primary" >
	        做一单!
	    </button>
	</div></a>
	    </li>
	    <li class="ui-border-t"><a href="inventory.jsp">
	<div class="ui-btn-wrap">
	    <button class="ui-btn-lg ui-btn-primary">
	        库存
	    </button>
	</div></a>
	    </li>
	    <li class="ui-border-t"><a href="order.jsp">
	<div class="ui-btn-wrap">
	    <button class="ui-btn-lg ui-btn-primary">
	        订单
	    </button>
	</div></a>
	    </li>
	</ul>

</div>
</body>
</html>
