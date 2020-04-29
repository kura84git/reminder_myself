<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reminder</title>
</head>
<body>
<h1>リマインダーアプリケーションへようこそ</h1>
<form action="/reminder_myself/Login" method="post">
	<p>ユーザID：<input type="text" name="id"></p>
	<p>パスワード：<input type="password" name="pass"></p>
	<input type="submit" value="送信！">
</form>

<p><a href="/reminder_myself/AccountRegister">新規登録はこちら</a></p>

</body>
</html>