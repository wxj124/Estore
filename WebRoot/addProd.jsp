<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>

<body style="text-align: center">
<div align="center">
	<h1>Estore_添加商品</h1>
	<hr>
	<form action="${pageContext.request.contextPath }/AddProdServlet"
		method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<td>商品名称</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>商品价格</td>
				<td><input type="text" name="price" /></td>
			</tr>
			<tr>
				<td>商品类型</td>
				<td><select name="category">
					<option value="图书杂志">图书杂志</option>
					<option value="家电">家电</option>
					<option value="日杂">日杂</option>
					<option value="床上用品">床上用品</option>
				</select></td>
			</tr>
			<tr>
				<td>库存数量</td>
				<td><input type="text" name="pnum" /></td>
			</tr>
			<tr>
				<td>商品图片</td>
				<td><input type="file" name="file1" /></td>
			</tr>
			<tr>
				<td>商品描述</td>
				<td><textarea name="description" cols="30" rows="6"></textarea></td>
			</tr>
			<tr><td colspan="2"><input type="submit" name="" value="添加"/></td></tr>
		</table>
	</form></div>
</body>
</html>
