package com.cloud.bot;

public enum BotCommands {
	START("/start"), 
	ACTIONS("/actions"),
	HELP("/help");
	
	private String command;
	
	BotCommands(String command) {
		this.command = command;
	}
	
	public String getCommand() {
		return command;
	}
}
