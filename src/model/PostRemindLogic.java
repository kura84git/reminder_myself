package model;

import java.util.List;

public class PostRemindLogic {

	public void execute(Remind remindLatest, List<Remind> remindList) {

		remindList.add(0, remindLatest); //送信されたリマインドをリマインドリストの先頭に追加

	}

}
