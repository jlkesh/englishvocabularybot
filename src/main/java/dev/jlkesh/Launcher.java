package dev.jlkesh;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Launcher {
    public static void main(String[] args) {
        try {
            var registry = new TelegramBotsApi(DefaultBotSession.class);
            var bot = VocabularyBuilderBot.getInstance();
            registry.registerBot(bot);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}