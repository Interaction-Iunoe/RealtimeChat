package edu.ut.inlab.chat.service;

import java.util.Map;

import edu.ut.inlab.chat.bean.ChatConnection;

public interface ChatManager {
	
	/**
	 * @param name
	 * @return
	 */
	boolean registerMyName(String sender);
	
	/**
	 * @param sender
	 * @param receiver
	 * @return
	 */
	Map<String, Object> createChatConnection(String sender, String receiver);
	
	/**
	 * @param id
	 * @param String
	 * @return
	 */
	boolean sendMessage(String id, String message);
	
	/**
	 * @param id
	 * @return
	 */
	ChatConnection getConnection(String id);
}
