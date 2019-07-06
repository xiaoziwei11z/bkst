<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="conf.serverURL" var="serverURL"/>
<fmt:message key="portal_server" var="portalServer" bundle="${serverURL}"/>
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
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<h3 class="text-center">
					用户登录
				</h3>
				<br/><br/>
				<form class="form-horizontal" role="form" method="post" action="/login.html">
					<div class="form-group">
						 <label for="inputEmail3" class="col-sm-1 control-label">用户名</label>
						<div class="col-sm-11">
							<input type="text" class="form-control" id="inputEmail3" name="username" value="${username }"/>
						</div>
					</div>
					<div class="form-group">
						 <label for="inputPassword3" class="col-sm-1 control-label">密码</label>
						<div class="col-sm-11">
							<input type="password" class="form-control" id="inputPassword3" name="password" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-1 col-sm-11">
							 <button type="submit" class="btn btn-default">登录</button>
							 <h4>
								<a href="/toRegister.html">注册账号</a>
							</h4><br/>
							<h4>
								<a href="${portalServer }index.html">前往商城首页</a>
							</h4><br/>
						</div>
					</div>
				</form>
				<h3 class="text-warning">
					${msg }
				</h3>
			</div>
		</div>
	</div>
</body>
</html>