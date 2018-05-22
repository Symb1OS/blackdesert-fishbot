package ru.namibios.arduino.model;

public class Action {

	private static final String PM = "%s[%s,%s]";
	
	private String command;

	private String username;

	private String message;
	
	public Action() {}
	
	public Action(String command, String username, String message) {
		this.command = command;
		this.username = username;
		this.message = message;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Action [command=" + command + ", username=" + username + ", message=" + message + "]";
	}
	
	public String toPersonalMessage() {
		return String.format(PM, command, username, message);
	}

}
