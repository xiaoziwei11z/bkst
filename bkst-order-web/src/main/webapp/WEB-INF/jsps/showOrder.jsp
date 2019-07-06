<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="conf.serverURL" var="serverURL"/>
<fmt:message key="portal_server" var="portalServer" bundle="${serverURL }"/>
<fmt:message key="account_server" var="accountServer" bundle="${serverURL }"/>
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
				<h3 class="text-center">
					所有订单
				</h3><br/><br/>
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
								<a href="${portalServer }exitLogin.html">退出</a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<br/><br/>
				<table class="table">
					<thead>
						<tr>
							<th>
								订单编号
							</th>
							<th>
								图书名称
							</th>
							<th>
								单价
							</th>
							<th>
								数目
							</th>
							<th>
								总价
							</th>
							<th>
								地址
							</th>
							<th>
								订单状态
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orderList}" var="order">
							<tr>
								<td>
									${order.id }
								</td>
								<td>
									<a href="${portalServer}bookDetail/${order.bid}.html">${order.bname }</a>
								</td>
								<td>
									${order.price }
								</td>
								<td>
									${order.count }
								</td>
								<td>
									${order.total }
								</td>
								<td>
									${order.address }
								</td>
								<td>
									<c:choose>
										<c:when test="${order.status==0}">未付款</c:when>
										<c:when test="${order.status==1 }">已付款待发货</c:when>
										<c:when test="${order.status==2 }">已发货</c:when>
										<c:when test="${order.status==3 }">交易成功</c:when>
									</c:choose>
								</td>
								<td>
									<c:choose>
										<c:when test="${order.status==0 }">
											<a href="/pay/${order.id }.html">支付</a>
										</c:when>
										<c:when test="${order.status==2 }">
											<a href="/receive/${order.id }.html">确认收货</a>
										</c:when>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<h4>
					<a href="${portalServer }index.html">返回主页</a>
				</h4>
			</div>
		</div>
	</div>
</body>
</html>