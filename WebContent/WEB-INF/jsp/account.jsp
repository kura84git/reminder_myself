<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String notConfirm = (String)request.getAttribute("notConfirm");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reminder</title>
</head>
<body>
<h1>リマインダー</h1>
<h2>新規会員登録</h2>

<% if(notConfirm != null) { %>
<p><%= notConfirm %></p>
<% } %>

<form action="/reminder_myself/AccountRegister" method="post">
	<p>ユーザID：<input type="text" name="id"></p>
	<p>パスワード：<input type="password" name="pass"></p>
	<input type="submit" value="アカウント登録">
</form>

<p><a href="/reminder_myself/index.jsp">メインメニューに戻る</a></p>

</body>
</html>