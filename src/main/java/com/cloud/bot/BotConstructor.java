package com.cloud.bot;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.cloud.core.BotConfig;
import com.cloud.core.BotResponse;

public class BotConstructor extends TelegramLongPollingBot{

	public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            if (update.getMessage().getText().equals(BotCommands.ACTIONS.getCommand())) {
                SendMessage message = new SendMessage() 
                        .setChatId(chatId)
                        .setText(BotResponse.ACTIONS_RESPONCE);
                sendButtonsForActions(message);
                try {
                    execute(message); 

                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {
            }
        } else if (update.hasCallbackQuery()) {
            String callData = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            
            if (callData.equals("archive")) {
                String answer = "Updated message text";
                EditMessageText new_message = new EditMessageText()    

                        .setChatId(chatId)
                        .setMessageId((int) messageId)
                        .setText(answer);
                try {
                    execute(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	
	
	private void sendButtonsForActions(SendMessage message) {
		InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
		message.setReplyMarkup(keyboard);
		List<List<InlineKeyboardButton>> rowButtonsList = new ArrayList<List<InlineKeyboardButton>>();
		List<InlineKeyboardButton> inlineKB = new ArrayList<InlineKeyboardButton>();
		InlineKeyboardButton archiveKey = new InlineKeyboardButton();
		InlineKeyboardButton unzipKey = new InlineKeyboardButton();
		inlineKB.add(archiveKey.setText("Archive").setCallbackData("archive"));
		inlineKB.add(unzipKey.setText("Unzip").setCallbackData("unzip"));
		rowButtonsList.add(inlineKB);
		
		actionsWithArchives(inlineKB);
		keyboard.setKeyboard(rowButtonsList);
	}

	private synchronized void actionsWithArchives(List<InlineKeyboardButton> list) {
		if(list.get(0).getText() == "Archive" || list.get(1).getText() == "Unzip") {
			
		}
	}
	
	public String getBotUsername() {
		return BotConfig.BOT_USERNAME;
	}

	@Override
	public String getBotToken() {
		return BotConfig.BOT_TOKEN;
	}
	
}
