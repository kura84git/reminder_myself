package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import account.Account;
import model.PostRemindLogic;
import model.Remind;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		//⓪アプリケーションスコープを定義。リマインドリストを新規作成してアプリケーションスコープに保存。
		ServletContext application = this.getServletContext();
		List<Remind> remindList = (List<Remind>)application.getAttribute("remindList");
		if(remindList == null) {//remindListを初回のみ作成するように修正(2020/04/26[mori])
			remindList =  new ArrayList<>(); // Remind.javaはJavaBeans。
			application.setAttribute("remindList", remindList);
		}

		//ログインしているか確認するためセッションスコープからユーザー情報を取得 (2020/04/29[kurahashi])
		HttpSession session = request.getSession();
		Account loginAccount = (Account) session.getAttribute("loginAccount");

		//(2020/04/29[kurahashi])
		if(loginAccount == null) { //ログインしていない場合

			//index.jspへリダイレクト
			response.sendRedirect("/reminder_myself/index.jsp");

		}else { //ログイン済みの場合

			//①main.jspへフォワード
			String path = "/WEB-INF/jsp/main.jsp";  //(2020/04/26[mori])
			RequestDispatcher dis = request.getRequestDispatcher(path);
			dis.forward(request, response);

		}



	} //doGet()




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//③main.jspからのリクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String remind = request.getParameter("remind");

		//④入力値チェックをする。値があれば、アプリケーションスコープに保存する処理を行っていく。
		if(remind != null &&  remind.length() != 0) {

			//アプリケーションスコープを定義し、現在格納されているリマインド情報を取得。
			ServletContext application = this.getServletContext();
			List<Remind> remindList = (List<Remind>)application.getAttribute("remindList");

			//入力値をJavaBeansに格納
			Remind remindLatest = new Remind(remind);

			//PostRemindLogicクラスのインスタンスを生成。
			PostRemindLogic postRemindLogic = new PostRemindLogic();
			//PostRemindLogicの、送信されたリマインドをリマインドリストの先頭に追加するexecuteメソッドを呼び出す。
			postRemindLogic.execute(remindLatest, remindList);

			//アプリケーションスコープに保存
			application.setAttribute("remindList", remindList);

		}else {//例外処理の部分を追加(2020/04/26[mori])
			request.setAttribute("errorMsg", "Please input text!");
		}


		//⑤main.jspへフォワード
		String path = "/WEB-INF/jsp/main.jsp";  //(2020/04/26[mori])
		RequestDispatcher dis = request.getRequestDispatcher(path);
		dis.forward(request, response);


	} //doPost()

}
