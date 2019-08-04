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

	private void senderConstruct() {
		// TODO Auto-generated method stub

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
			setButtonsForActions(messageData);
			sendMessage(messageData);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private void setButtonsForActions(SendMessage message) {
		InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> kb = new ArrayList<List<InlineKeyboardButton>>();
		List<InlineKeyboardButton> inlineKB = new ArrayList<InlineKeyboardButton>();
		inlineKB.add(new InlineKeyboardButton().setText("Archive").setCallbackData("Hello"));
		inlineKB.add(new InlineKeyboardButton().setText("Archive").setCallbackData("Hi"));
		kb.add(inlineKB);
		
		keyboard.setKeyboard(kb);
		message.setReplyMarkup(keyboard);

	}

	private synchronized void setButtonsForArchive() {
		//TODO: will set buttons for archive
	}
	
	public String getBotUsername() {
		return "CloudBot";
	}

	@Override
	public String getBotToken() {
		return "924748393:AAErxKwKmjVDLdD6a-8F_u9eHKwWB8mN4pM";
	}
	
}
