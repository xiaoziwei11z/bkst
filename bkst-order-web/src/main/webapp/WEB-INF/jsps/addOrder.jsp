<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="conf.serverURL" var="serverURL"/>
<fmt:message key="account_server" var="accountServer" bundle="${serverURL }"/>
<fmt:message key="portal_server" var="portalServer" bundle="${serverURL }"/>
<!DOCTYPE html>
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap-theme.css" rel="stylesheet">
	<link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.css" rel="stylesheet">
	<script src="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/js/affix.js"></script>
	<script src="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/js/alert.js"></script>
	<script src="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/js/bootstrap.js"></script>
	<script src="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/js/button.js"></script>
	<script src="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/js/carousel.js"></script>
	<script src="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/js/collapse.js"></script>
	<script src="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/js/dropdown.js"></script>
	<script src="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/js/modal.js"></script>
	<script src="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/js/popover.js"></script>
	<script src="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/js/scrollspy.js"></script>
	<script src="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/js/tab.js"></script>
	<script src="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/js/tooltip.js"></script>
	<script src="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/js/transition.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body><br/>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="row clearfix">
					<div class="col-md-12 column">
						<h3 class="text-center">
							添加订单
						</h3><br/><br/>
					</div>
				</div>
				<div class="row clearfix">
					<div class="col-md-4 column">
					</div>
					<div class="col-md-4 column">
					</div>
					<div class="col-md-4 column">
						<c:choose>
							<c:when test="${empty sessionScope.session_user }">
								<a href="${accountServer }index.html">登录</a>&nbsp&nbsp&nbsp&nbsp
								<a href="${accountServer }toRegister.html">注册</a>
							</c:when>
							<c:otherwise>
								${sessionScope.session_user.username }&nbsp&nbsp&nbsp&nbsp
								<a href="/showCart.html">我的购物车</a>&nbsp&nbsp&nbsp&nbsp
								<a href="/showOrder.html">我的订单</a>&nbsp&nbsp&nbsp&nbsp
								<a href="${portalServer}exitLogin.html">退出</a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<br/>
				<form role="form" method="post" action="/addOrder/${book.bid}/${count}.html">
					<div class="form-group">
						 <label>图书名称:${book.bname }</label>
					</div>
					<div class="form-group">
						 <label>单价:${book.price }</label>
					</div>
					<div class="form-group">
						 <label>购买数量:${count }</label>
					</div>
					<div class="form-group">
						 <label>总金额:${book.price*count }</label>
					</div>
					<div class="form-group">
						 <label>地址</label>
						 <input type="text" class="form-control" name="address"/>
					</div>
					<button type="submit" class="btn btn-default">提交</button>
					<input type="hidden" name="cartId" value="${cartId }"/>
				</form>
				<h4>
					<a href="${portalServer }">返回首页</a>
				</h4><br/>
				<h3 class="text-warning">
					${msg }
				</h3>
			</div>
		</div>
	</div>
</body>
</html>