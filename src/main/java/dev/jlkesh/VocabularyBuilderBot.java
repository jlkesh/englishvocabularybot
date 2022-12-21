package dev.jlkesh;

import dev.jlkesh.handlers.callback.CallBackHandler;
import dev.jlkesh.handlers.Handler;
import dev.jlkesh.handlers.message.MessageHandler;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;
import java.util.ResourceBundle;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VocabularyBuilderBot extends TelegramLongPollingBot {


    private static VocabularyBuilderBot instance;
    private final ResourceBundle settings = ResourceBundle.getBundle("application");
    private final Handler messageHandler = MessageHandler.getInstance();
    private final Handler callBackHandler = CallBackHandler.getInstance();

    @Override
    public String getBotToken() {
        return settings.getString("bot.token");
    }



    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage())
                messageHandler.process(update);
            else if (update.hasCallbackQuery())
                callBackHandler.process(update);
            else {
                deleteMessage(update);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteMessage(Update update) {
        Message message = update.getMessage();
        executeMessage(new DeleteMessage(message.getChatId().toString(), message.getMessageId()));
    }


    @SuppressWarnings("rawtypes")
    public void executeMessage(BotApiMethod message) {
        try {
            super.execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return settings.getString("bot.username");
    }

    public static VocabularyBuilderBot getInstance() {
        if (instance == null) {
            instance = new VocabularyBuilderBot();
        }
        return instance;
    }
}
