<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="account.Account" %>
<%
//セッションスコープからユーザ情報を取得
Account loginAccount = (Account) session.getAttribute("loginAccount");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reminder</title>
</head>
<body>

<h1>リマインダーアプリケーションログイン</h1>
<% if(loginAccount != null) { %>
	<p>ログインに成功しました</p>
	<p>ようこそ<%= loginAccount.getId() %>さん</p>
	<p><a href="/reminder_myself/Main">リマインダーアプリケーションメインページへ</a></p> <!-- ⑥Mainサーブレットへ -->
<% } else { %>
	<p>ログインに失敗しました</p>
	<p><a href="/reminder_myself/index.jsp">TOPへ</a></p> <!-- index.jspへやり直し -->
<% } %>


</body>
</html>