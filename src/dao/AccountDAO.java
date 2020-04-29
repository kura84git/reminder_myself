package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import account.Account;

public class AccountDAO {

	private final String JDBC_URL = "jdbc:mysql://localhost:3306/reminder?characterEncoding=utf-8&serverTimezone=JST";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";


	public boolean findByLogin(Account account) { //ユーザIDが既に使われているかどうかを確認するメソッド

		Connection conn = null;

		try {

			//データベースにつなぐ処理
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//SELECT文の準備
			String sql = "SELECT USER_ID,PASS FROM ACCOUNT WHERE USER_ID = ?";

			//SQL送信
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, account.getId());

			ResultSet rs = pStmt.executeQuery();

			if(rs.next()) { //IDが既に使われている場合
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}

		return true; //IDが未使用の場合



	}//findByLogin()


	public boolean create(Account account) { //ACCOUNTテーブルに新規アカウント情報をINSERTするメソッド

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//SQL文の準備
			String sql = "INSERT INTO ACCOUNT(USER_ID,PASS) VALUES(?,?)";

			//SQL文を送る
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//SQL文に情報を渡す
			pStmt.setString(1, account.getId());
			pStmt.setString(2, account.getPass());


			//SQL文を実行して結果を取得する(rsには追加された行数が代入される)
			int rs = pStmt.executeUpdate();

			if(rs != 1) {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}



		return true; //insert文が実行完了できた！！





	}//create()

}
