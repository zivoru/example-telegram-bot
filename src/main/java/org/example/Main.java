package org.example;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class Main {
    public static void main(String[] args) {
        var bot = new TelegramBot("token");
        bot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                if (update.message().text().equals("/start")) {
                    bot.execute(new SendMessage(update.message().chat().id(),
                            "Hello Bot!"));
                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}