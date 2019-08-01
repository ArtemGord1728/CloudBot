package com.cloud.bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class BotMain {
	public static void main(String[] args) {

		ApiContextInitializer.init();
		BotConstructor bot = new BotConstructor();
	    TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
	    try {
	        telegramBotsApi.registerBot(bot);
	    } catch (TelegramApiException e) {
	        e.printStackTrace();
	    }
	}
}
