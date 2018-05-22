package ru.namibios.arduino;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.Action;
import ru.namibios.arduino.model.command.Command;
import ru.namibios.arduino.utils.JSON;
import ru.namibios.arduino.utils.Keyboard;

@ClientEndpoint
public class ActionClientSocket {

	private static final Logger logger = Logger.getLogger(ActionClientSocket.class);
	
	public ActionClientSocket() {

		try{
			
	        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
	        container.connectToServer(this, new URI(Application.getInstance().WS_ACTION()));
	        
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@OnOpen
	public void onOpen(Session session) {
		logger.info("Connection open..");
	}
	
	@OnClose
	public void onClose(Session session) {
		logger.info("Connection closed..");
	}
	
	@OnMessage
	public void onMessage(Session session, String message) throws JsonParseException, JsonMappingException, IOException {
		
		Action action = JSON.getInstance().readValue(message, Action.class);
		logger.info("[Remote control] - " + action);
		
		String command = action.getCommand();
		if (command.equals("Exit")) {
			Command com = () -> command;
			Keyboard.send(com);
		}
		if(command.equals("PM")) {
			Command com = () -> action.toPersonalMessage();
			Keyboard.send(com);
		} 
	}
	
	@OnError
	public void onError(Throwable t) {
		t.printStackTrace();
	}
	
}