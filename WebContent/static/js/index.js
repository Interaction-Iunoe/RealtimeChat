var myName=null;
var friendName=null;
var connectionId=null;

$(document).ready(function() {
	$("#register-my-name").click(function() {
		myName=$("#chat-my-name").val();
		if(myName==""||myName==null) {
			$.messager.popup("My name is empty");
			return;
		} 
		ChatManager.registerMyName(myName, function(success) {
			if(success) {
				$.messager.popup("Register name success!");
			} else {
				$.messager.popup("Your name has been registerd.");
			}
		});
		
	});

	$("#create-connection").click(function() {
		friendName=$("#chat-friend-name").val();
		if(myName==null) {
			myName=$("#chat-my-name").val();
		}
		if(friendName==null||friendName=="") {
			$.messager.popup("Friend name is empty");
			return;
		}
		ChatManager.createChatConnection(myName, friendName, function(data) {
			console.log(data);
			if(data.success) {
				$.messager.popup("Create connection success!");
				connectionId=data.id;
			} else {
				$.messager.popup("Create connection Failed!");
			}
		});
	});

	$("#send-message").click(function() {
		var message=$("#chat-message").val();
		if(message==null||message=="") {
			$.messager.popup("Message is empty");
			return;
		}
		ChatManager.sendMessage(connectionId, message, function(success) {
			if(success) {
				$("#chat-content").html($("#chat-content").html()+message+"<br>");
			} else {
				$.messager.popup("Send Message Failed!");
			}
		});
	});

	$("#refresh-content").click(function() {
		ChatManager.getConnection(connectionId, function(connection) {
			var messages=connection.messages;
			if (messages==null||messages.length==0){
				return;
			}
			var html="";
			for(var i=messages.length-1; i>=0; i--) {
				html+=messages[i]+"<br>";
			}
			$("#chat-content").html(html);
		});
	});
	
	$("#clear-messege").click(function() {
		$.messager.confirm("Warning","Are you sure to clear?", function() {
			ChatManager.clearMessege(connectionId, function(success) {
				if(success){
					$.messager.popup("Message is Cleared!");
					$("#chat-content").html("");
				}else {
					$.messager.popup("Failed to Clear!");
				}
					
			});
		});
		
	});
});