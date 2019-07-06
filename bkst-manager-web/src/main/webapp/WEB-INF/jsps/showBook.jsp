<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
					图书列表
				</h3><br/><br/>
				<ul class="nav nav-tabs">
					<li>
						 <a href="/showBook.html">所有分类</a>
					</li>
					<c:forEach items="${categoryList}" var="category">
						<li>
							 <a href="/showBook.html?cid=${category.cid }">${category.cname }</a>
						</li>
					</c:forEach>
				</ul><br/><br/>
				<div class="row">
					<c:forEach items="${bookList}" var="book">
						<div class="col-md-4">
							<div class="thumbnail">
								<img style="height: 200px;width: 200px" src="${book.picture }" />
								<div class="caption">
									<h3>
										${book.bname }
									</h3>
									<p>
										作者：${book.author }
									</p>
									<p>
										单价：${book.price }
									</p>
									<p>
										分类：${book.category.cname }
									</p>
									<p>
										详细介绍：${book.text }
									</p>
									<p>
										 <a class="btn btn-primary" href="/toEditBook/${book.bid }.html">修改</a> 
										 <a class="btn" href="/deleteBook/${book.bid }.html?cid=${cid}">删除</a>
									</p>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<h4>
					<a href="/index.html">返回主页</a>
				</h4>
			</div>
		</div>
	</div>
</body>
</html>