<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<body>
	<h1>Estore_商品信息</h1>
	<hr>
	<table width="100%" >
	<tr><td  width="40%"><font color="purple">商品展示</font></td><td  width="40%"><font color="purple" >商品信息</font></td><td  width="20%"><font color="purple">有无商品</font></td></tr>
  	<tr>
				<td colspan="3"><hr></td>
			</tr>
		<c:forEach items="${requestScope.list }" var="prod">
			<tr>
				<td width="40%"><img src="${pageContext.request.contextPath }/ImgServlet?imgurl=${prod.imgurls }" ></td>
				<td width="40%">商品名：${prod.name }<br><br>价格：${prod.price }<br><br>类型：${prod.category }<br><br></td>
				<td width="20%"><c:if test="${prod.pnum>0 }">
						<font color="blue">有货</font>
					</c:if> <c:if test="${prod.pnum<=0 }">
						<font color="red">缺货</font>
					</c:if></td>
			</tr>
		 	<tr>
				<td colspan="3"><hr></td>
			</tr> 
		</c:forEach>
	</table>
</body>
</html>
