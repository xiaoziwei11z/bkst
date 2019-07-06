<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>bkst网上书店后台管理系统</title>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<h3 class="text-center">
					bkst网上书店后台管理系统
				</h3><br/><br/>
				<div class="row clearfix">
					<div class="col-md-4 column">
					</div>
					<div class="col-md-4 column">
					</div>
					<div class="col-md-4 column">
						${sessionScope.session_user.username }&nbsp&nbsp&nbsp&nbsp
						<a href="/exitLogin.html">退出</a>
					</div>
				</div>
			</div>
		</div>
		<br/><br/>
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="panel-group" id="panel-207029">
					<div class="panel panel-default">
						<div class="panel-heading">
							 <a class="panel-title collapsed" href="#panel-element-632312" data-toggle="collapse" data-parent="#panel-207029">图书分类管理</a>
						</div>
						<div class="panel-collapse collapse" id="panel-element-632312">
							<div class="panel-body">
								<a href="/toAddCategory.html">添加分类</a>
							</div>
							<div class="panel-body">
								<a href="/showCategory.html">查询分类</a>
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							 <a class="panel-title collapsed" href="#panel-element-271705" data-toggle="collapse" data-parent="#panel-207029">图书管理</a>
						</div>
						<div class="panel-collapse collapse" id="panel-element-271705">
							<div class="panel-body">
								<a href="/toAddBook.html">添加图书</a>
							</div>
							<div class="panel-body">
								<a href="/showBook.html">查询图书</a>
							</div>
							<div class="panel-body">
								<a href="/toImportBook.html">导入图书索引</a>
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							 <a class="panel-title collapsed" href="#panel-element-271706" data-toggle="collapse" data-parent="#panel-207029">订单管理</a>
						</div>
						<div class="panel-collapse collapse" id="panel-element-271706">
							<div class="panel-body">
								<a href="/showOrder.html">查询订单</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>