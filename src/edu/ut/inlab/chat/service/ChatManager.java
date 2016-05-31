package edu.ut.inlab.chat.service;

import java.util.Map;

import edu.ut.inlab.chat.bean.ChatConnection;

public interface ChatManager {
	
	/**
	 * 保存姓名
	 * @param name
	 * @return
	 */
	boolean registerMyName(String sender);
	
	/**
	 * 创建聊天链接
	 * @param sender
	 * @param receiver
	 * @return
	 */
	Map<String, Object> createChatConnection(String sender, String receiver);
	
	/**
	 * 发送信息
	 * @param id
	 * @param String
	 * @return
	 */
	boolean sendMessage(String id, String message);
	
	/**
	 * 建立链接
	 * @param id
	 * @return
	 */
	ChatConnection getConnection(String id);
	
	/**
	 * 清空信息
	 * @param id
	 * @return
	 */
	boolean clearMessege(String id);
	
}
