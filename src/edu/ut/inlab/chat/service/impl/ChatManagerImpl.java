package edu.ut.inlab.chat.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import edu.ut.inlab.chat.bean.ChatConnection;
import edu.ut.inlab.chat.service.ChatManager;

public class ChatManagerImpl implements ChatManager {
	
	private List<String> users=null;
	private List<ChatConnection> connections=null;

	@Override
	public boolean registerMyName(String sender) {
		if(users==null) {
			users=new ArrayList<>();
		}
		if(users.contains(sender)) {
			return false;
		}
		users.add(sender);
		return true;
	}

	@Override
	public Map<String, Object>  createChatConnection(String sender, String receiver) {
		if(connections==null) {
			connections=new ArrayList<>();
		}
		Map<String, Object>  data=new HashMap<>();
		if(!users.contains(receiver)||!users.contains(sender)) {
			data.put("success", false);
			return data;
		}
		ChatConnection myConnection=null;
		for(ChatConnection connection: connections) {
			if((connection.getSender().equals(sender)&&connection.getReceiver().equals(receiver))
					||(connection.getSender().equals(receiver)&&connection.getReceiver().equals(sender))) {
				myConnection=connection;
				break;
			}
		}
		if(myConnection!=null) {
			data.put("success", true);
			data.put("id", myConnection.getId());
			return data;
		}
		myConnection=new ChatConnection();
		myConnection.setId(UUID.randomUUID().toString());
		myConnection.setSender(sender);
		myConnection.setReceiver(receiver);
		connections.add(myConnection);
		data.put("id", myConnection.getId());
		data.put("success", true);
		return data;
	}

	@Override
	public boolean sendMessage(String id, String message) {
		ChatConnection myConnection=findConnection(id);
		if(myConnection==null) {
			return false;
		}
		if(myConnection.getMessages()==null) {
			myConnection.setMessages(new ArrayList<>());
		}
		myConnection.getMessages().add(message);
		return true;
	}

	@Override
	public ChatConnection getConnection(String id) {
		return findConnection(id);
	}
	
	private ChatConnection findConnection(String id) {
		ChatConnection myConnection=null;
		for(ChatConnection connection: connections) {
			if(connection.getId().equals(id)) {
				myConnection=connection;
				break;
			}
		}
		return myConnection;
	}
}
