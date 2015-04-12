<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
  <h1>Estore</h1><hr>
  <c:if test="${sessionScope.user==null }">
                   欢迎您！ <a href="${pageContext.request.contextPath }/regist.jsp">注册</a>
           <a href="${pageContext.request.contextPath }/login.jsp">登录</a>
  </c:if>
 <c:if test="${sessionScope.user!=null }">
                   欢迎您,${sessionScope.user.username}回来 ！ <a href="${pageContext.request.contextPath }/addProd.jsp">添加商品</a>
                   <a href="${pageContext.request.contextPath }/ListProdServlet">商品列表</a>
                   <a href="${pageContext.request.contextPath }/LogoutServlet">注销</a>
  </c:if>
  </body>
</html>
