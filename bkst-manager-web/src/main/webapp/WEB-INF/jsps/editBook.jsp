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
					修改图书
				</h3>
				<br/><br/>
				<form role="form" method="post" action="/editBook.html" enctype="multipart/form-data">
					<input type="hidden" name="bid" value="${book.bid }"/>
					<div class="form-group">
						 <label>图书名称</label>
						 <input type="text" class="form-control" name="bname" value="${book.bname }"/>
					</div>
					<div class="form-group">
						 <label>作者</label>
						 <input type="text" class="form-control" name="author" value="${book.author }" />
					</div>
					<div class="form-group">
						 <label>单价</label>
						 <input type="text" class="form-control" name="price" value="${book.price }" />
					</div>
					<input type="hidden" name="lastCid" value="${book.category.cid }"/>
					<div class="form-group">
						 <label>选择分类</label><br/>
				    	<select name="cid" style="width:150px; height:20px;">
				    		<option value="0">请选择</option>
				    		<c:forEach items="${categoryList }" var="category">
				    			<c:choose>
				    				<c:when test="${category.cid == book.category.cid }">
				    					<option value="${category.cid }" selected="selected">${category.cname }</option>
				    				</c:when>
				    				<c:otherwise>
				    					<option value="${category.cid }">${category.cname }</option>
				    				</c:otherwise>
				    			</c:choose>
				    		</c:forEach>
				    	</select>
					</div>
					<div class="form-group">
						 <label>原图片</label><br/>
						 <img style="width: 200px; height: 200px;" src="${book.picture }"/>
					</div>
					<input type="hidden" name="picture" value="${book.picture }"/>
					<div class="form-group">
						 <label>修改图片</label>
						 <input type="file" name="pictureFile"/>
					</div>
					<div class="form-group">
						 <label>详细描述</label>
						 <input type="text" class="form-control" name="text"  value="${book.text }"/>
					</div>
					<button type="submit" class="btn btn-default">提交</button>
				</form>
				<h4>
					<a href="/showBook.html">返回图书列表</a>
				</h4><br/>
				<h3 class="text-warning">
					${msg }
				</h3>
			</div>
		</div>
	</div>
</body>
</html>