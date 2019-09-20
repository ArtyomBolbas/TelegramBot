package com.bolbas.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.bolbas.app.dao.RefInfoRepo;
import com.bolbas.app.entity.ReferenceInformation;

@Component
public class TelegaBot extends TelegramLongPollingBot {

	@Autowired
	private RefInfoRepo refInfoRepo;

	private static final String NOT_EXISTS = "The city and information about it does not exist";
	private static final String BOT_NAME = "cityInfoSpring_bot";
	private static final String TOKEN = "976278621:AAEZPQOUoSMLRvB517169FPex741wwcfuYs";

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {
			String message_text = update.getMessage().getText();
			String answer = null;
			ReferenceInformation refInfo = refInfoRepo.findByCity(message_text);
			if (refInfo != null && !refInfo.getDescription().isEmpty()) {
				answer = refInfo.getDescription();
			} else {
				answer = NOT_EXISTS;
			}
			long chat_id = update.getMessage().getChatId();

			SendMessage message = new SendMessage().setChatId(chat_id).setText(answer);
			try {
				execute(message);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getBotUsername() {
		return BOT_NAME;
	}

	@Override
	public String getBotToken() {
		return TOKEN;
	}

}
