package model;

import java.io.Serializable;

public class Remind implements Serializable {

	private String remind; //リマインド内容

	public Remind(String remind) { //setterの役割を持つコンストラクタ
		this.remind = remind;
	}

	public String getRemind() {
		return remind;
	}



}
