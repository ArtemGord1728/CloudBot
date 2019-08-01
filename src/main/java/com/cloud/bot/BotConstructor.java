package com.cloud.bot;

import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class BotConstructor extends TelegramLongPollingBot{

	public void onUpdateReceived(Update update) {
		
	}

	public String getBotUsername() {
		return null;
	}

	@Override
	public String getBotToken() {
		return null;
	}
	
}
