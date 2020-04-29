package account;

import java.io.Serializable;

public class Account implements Serializable{ //JavaBeansである。

	private String id; //アカウント情報(ID)
	private String pass; //アカウント情報(パスワード)

	public Account() {} //空のコンストラクタ

	public Account(String id, String pass) { //setterの役割をもつコンストラクタ
		this.id = id;
		this.pass = pass;
	}

	public String getId() { //IDのgetter
		return id;
	}

	public String getPass() { //パスワードのgetter
		return pass;
	}


}
