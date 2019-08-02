package com.cloud.bot;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
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
	private synchronized void sendMessage(String chatId, String message) {
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
	private synchronized void sendHelp(String chatId, String message) {
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
	private synchronized void sendActions(String chatId, String message) {
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
	
	
	
	private synchronized void setButtonsForActions(SendMessage message) {
		ReplyKeyboardMarkup keybord = new ReplyKeyboardMarkup();
		message.setReplyMarkup(keybord);
		List<KeyboardRow> buttonsList = new ArrayList<KeyboardRow>();
		keybord.setSelective(true);
		keybord.setResizeKeyboard(true);
		keybord.setOneTimeKeyboard(true);
		
		KeyboardRow firstKeyboardRow = new KeyboardRow();
		firstKeyboardRow.add(new KeyboardButton("Archive"));
		
	    KeyboardRow secondKeyboardRow = new KeyboardRow();
	    secondKeyboardRow.add(new KeyboardButton("Unzip"));
	    
	    buttonsList.add(firstKeyboardRow);
	    buttonsList.add(secondKeyboardRow);
	    
	    keybord.setKeyboard(buttonsList);
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
