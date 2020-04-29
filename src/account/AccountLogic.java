package account;

import dao.AccountDAO;

public class AccountLogic { //アカウント登録に関連する処理を行うModel

	public boolean execute(Account account) {
		AccountDAO dao = new AccountDAO();
		if(dao.findByLogin(account)) {
			return true;
		}

		return false;

	}//execute()

	public void create(Account account) {
		AccountDAO dao = new AccountDAO();
		dao.create(account); //新規アカウント情報をデータベースにINSERTする

	}//create()

}
