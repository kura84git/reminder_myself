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
import account.AccountLogic;


@WebServlet("/AccountRegister")
public class AccountRegister extends HttpServlet { //アカウント登録に関連する処理を行うController
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action"); //accountConfirm.jspから送られてきた情報を取得

		if(action == null) { //accountConfirm.jspで「戻る」をクリックした場合
			String path = "/WEB-INF/jsp/account.jsp";
			RequestDispatcher dis = request.getRequestDispatcher(path);
			dis.forward(request, response);
		}

		else if(action.equals("done")) { //accountConfirm.jspで「登録」をクリックした場合
			HttpSession session = request.getSession();
			Account account = (Account)session.getAttribute("account"); //セッションスコープから新規アカウント情報を取得
			AccountLogic accountLogic = new AccountLogic();
			accountLogic.create(account); //ユーザIDが既に使われているか判定する


			session.removeAttribute("account"); //新規アカウント情報を破棄

			String path = "/WEB-INF/jsp/accountDone.jsp"; //登録完了画面へ遷移
			RequestDispatcher dis = request.getRequestDispatcher(path);
			dis.forward(request, response);
		}





	}//doGet()



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//account.jspからリクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		Account account = new Account(id,pass);

		//セッションスコープ
		HttpSession session = request.getSession();
		session.setAttribute("account", account);

		AccountLogic accountLogic = new AccountLogic();
		boolean result = accountLogic.execute(account); //ユーザIDが既に使われているか判定する

		if(result) { //ユーザIDが使われていない場合
			String path = "/WEB-INF/jsp/accountConfirm.jsp";
			RequestDispatcher dis = request.getRequestDispatcher(path);
			dis.forward(request, response);
		} else { //ユーザIDが既に使われている場合

			String notConfirm = "そのIDは既に使われています。他のIDを入力してください。";
			request.setAttribute("notConfirm", notConfirm);
			String path = "/WEB-INF/jsp/account.jsp";
			RequestDispatcher dis = request.getRequestDispatcher(path);
			dis.forward(request, response);

		}




	}//doPost()

}
