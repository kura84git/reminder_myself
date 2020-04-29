<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="account.Account" %>
<%
Account account = (Account)session.getAttribute("account");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reminder</title>
</head>
<body>

<h1>ユーザ登録確認</h1>
<p>下記のユーザを登録します</p>

<p>ユーザID：<%= account.getId() %></p>
<p>パスワード：<%= account.getPass() %></p>

<p><a href="/reminder_myself/AccountRegister?action=done">登録</a></p>
<p><a href="/reminder_myself/AccountRegister">戻る</a></p>


</body>
</html>