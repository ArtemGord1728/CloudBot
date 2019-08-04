package com.cloud.bot;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.cloud.core.BotResponse;

public class BotConstructor extends TelegramLongPollingBot{

	public void onUpdateReceived(Update update) {
		String message = update.getMessage().getText();
		
		//TODO: Will update this code
		if(message.equals(BotCommands.START.getCommand())) {
			sendMessage(update.getMessage().getChatId().toString(), BotResponse.START_RESPONCE);
		}
		
		if(message.equals(BotCommands.ACTIONS.getCommand())) {
			sendActions(update.getMessage().getChatId().toString(), BotResponse.ACTIONS_RESPONCE);
		}
		
		if(message.equals(BotCommands.HELP.getCommand())) {
			sendHelp(update.getMessage().getChatId().toString(), BotResponse.HELP_RESPONCE);
		}
	}

	
	@SuppressWarnings("deprecation")
	private void sendMessage(String chatId, String message) {
		SendMessage messageData = new SendMessage();
		messageData.enableMarkdown(true);
		messageData.setChatId(chatId);
		messageData.setText(message);
		
		try {
			sendMessage(messageData);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	private void sendHelp(String chatId, String message) {
		SendMessage messageData = new SendMessage();
		messageData.enableMarkdown(true);
		messageData.setChatId(chatId);
		messageData.setText(message);
		
		try {
			sendMessage(messageData);
			
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	private void sendActions(String chatId, String message) {
		SendMessage messageData = new SendMessage();
		messageData.enableMarkdown(true);
		messageData.setChatId(chatId);
		messageData.setText(message);
		
		try {
			sendButtonsForActions(messageData);
			sendMessage(messageData);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private void sendButtonsForActions(SendMessage message) {
		InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
		message.setReplyMarkup(keyboard);
		List<List<InlineKeyboardButton>> rowButtonsList = new ArrayList<List<InlineKeyboardButton>>();
		List<InlineKeyboardButton> inlineKB = new ArrayList<InlineKeyboardButton>();
		InlineKeyboardButton archiveKey = new InlineKeyboardButton();
		InlineKeyboardButton unzipKey = new InlineKeyboardButton();
		inlineKB.add(archiveKey.setText("Archive").setCallbackData("Hello"));
		inlineKB.add(unzipKey.setText("Unzip").setCallbackData("Hi"));
		rowButtonsList.add(inlineKB);
		
		actions(inlineKB);
		keyboard.setKeyboard(rowButtonsList);
	}

	private synchronized void actions(List<InlineKeyboardButton> list) {
		if(list.get(0).getText() == "Archive" || list.get(1).getText() == "Unzip") {
			//TODO: Some actions
		}
	}
	
	public String getBotUsername() {
		return "CloudBot";
	}

	@Override
	public String getBotToken() {
		return "924748393:AAErxKwKmjVDLdD6a-8F_u9eHKwWB8mN4pM";
	}
	
}
