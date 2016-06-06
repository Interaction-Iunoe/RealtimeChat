package edu.ut.inlab.chat.service.impl;

import edu.ut.inlab.chat.service.NotificationManager;

public class NotificationManagerImpl implements NotificationManager {
	private boolean keyword=false;
	
	@Override
	public void keyword() {
		keyword=true;
	}

	@Override
	public boolean get() {
		return keyword;
	}

	@Override
	public void reset() {
		keyword=false;
	}

}
