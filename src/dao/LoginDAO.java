package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import account.Account;

public class LoginDAO {

	private final String JDBC_URL = "jdbc:mysql://localhost:3306/reminder?characterEncoding=utf-8&serverTimezone=JST";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";


	public boolean findByLogin(Account user) { //ユーザ名に対応するパスワードがACCOUNTテーブルの情報と一致しているかどうかを判定するメソッド

		Connection conn = null;

		try {
			//データベースにつなぐ処理
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//SELECT文の準備
			String sql = "SELECT USER_ID,PASS FROM ACCOUNT WHERE USER_ID=? AND PASS=?"; //データベースのアカウント情報をSELECT文で取得

			//SQL送信
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, user.getId()); //上記SELECT文の1番目の?に対応する
			pStmt.setString(2, user.getPass()); //上記SELECT文の2番目の?に対応する

			ResultSet rs = pStmt.executeQuery();

			if(rs.next()) { //IDとパスワードが一致している場合
				return true;
			}


		} catch(SQLException e) {
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


		return false; //IDとパスワードが間違っている場合

	}//findByLogin()


}
