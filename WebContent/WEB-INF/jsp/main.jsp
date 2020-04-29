<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Remind" %>
<%@ page import="account.Account" %>
<%@ page import="java.util.List" %>
<%
//セッションスコープに保存されたユーザー情報を取得
Account loginAccount = (Account) session.getAttribute("loginAccount");

//アプリケーションスコープに保存されたリマインドリストを取得
List<Remind> remindList = (List<Remind>)application.getAttribute("remindList");
//リクエストスコープに保存したエラーメッセージを取得(2020/04/26[mori])
String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reminder</title>
</head>
<body>
<h1>リマインダー</h1>

<p><%= loginAccount.getId() %>さん、ログイン中</p>

<p><a href="/reminder_myself/Logout">ログアウト</a></p> <!-- Logout.javaへ、セッションスコープを破棄して、logout.jspへ -->


<form action="/reminder_myself/Main" method="post"> <!-- ②リマインド内容をMainサーブレットにdoPost -->
	<p>リマインド内容：<input type="text" name="remind"></p>
	<p><input type="submit" value="リマインドを保存する！"></p>
</form>
<% if(errorMsg != null){ %><!--  errorLogの追記(2020/04/26[mori]) -->
	<p><%= errorMsg %></p>
<% } %>
<% for(Remind remind : remindList) { %>
	<p><%= remind.getRemind() %></p>
<% } %>
</body>
</html>