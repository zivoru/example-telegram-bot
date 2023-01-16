package org.example;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetMyCommands;

public class Main {
    public static void main(String[] args) {
        var bot = new TelegramBot("token");
        bot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                if (update.message().text().equals("/start")) {
                    Long chatId = update.message().chat().id();

                    var message1 = new SendMessage(chatId, "Hello Buttons #1");
                    message1.replyMarkup(new ReplyKeyboardMarkup(
                            new String[]{"Button #1", "Button #2"},
                            new String[]{"Button #3", "Button #4"},
                            new String[]{"Button #5"}
                    ));
                    bot.execute(message1);

                    var message2 = new SendMessage(chatId, "Hello Buttons #2");
                    message2.replyMarkup(new InlineKeyboardMarkup(
                            new InlineKeyboardButton("url").url("zivoru.ru"),
                            new InlineKeyboardButton("callback_data").callbackData("callback_data"),
                            new InlineKeyboardButton("Switch!").switchInlineQuery("switch_inline_query")
                    ));
                    bot.execute(message2);

                    var message3 = new SetMyCommands(
                            new BotCommand("/start", "started bot"),
                            new BotCommand("/subscribe", "Подпишись на канал"),
                            new BotCommand("/like", "Поставь лайк")
                    );
                    bot.execute(message3);
                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}