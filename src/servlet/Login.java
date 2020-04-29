package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import account.Account;
import login.LoginLogic;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		//Accountインスタンス(ユーザー情報)の生成
		Account account = new Account(id, pass);

		//ログイン処理LoginLogicのインスタンスを取得
		LoginLogic loginLogic = new LoginLogic();
		boolean isLogin = loginLogic.execute(account);

		//ログイン成功時の処理
		if(isLogin) {
		//ユーザー情報をセッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("loginAccount", account);
		}

		//⑤ログイン結果画面にフォワード
		String path = "/WEB-INF/jsp/loginResult.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);




	}//doPost()

}
