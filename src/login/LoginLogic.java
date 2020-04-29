package login;

import account.Account;
import dao.LoginDAO;

public class LoginLogic { //ログイン処理に関連する処理を行うModel
	public boolean execute(Account account) {
		LoginDAO dao = new LoginDAO();
		if(dao.findByLogin(account)) {
			return true;
		}

		return false;
	}

}
