package dev.jlkesh.handlers.callback;

import dev.jlkesh.handlers.Handler;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.sql.SQLException;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CallBackHandler implements Handler {
    @Getter
    private final static CallBackHandler instance = new CallBackHandler();
    private final Handler dailyQuizCallbackHandler = DailyQuizCallbackHandler.getInstance();
    private final Handler userRegisterCallbackHandler = RegisterUserCallbackHandler.getInstance();


    @Override
    public void process(Update update) throws SQLException {

        CallbackQuery callbackQuery = update.getCallbackQuery();

        Message message = callbackQuery.getMessage();
        User user = callbackQuery.getFrom();
        String callbackQueryData = callbackQuery.getData();
        if (callbackQueryData.startsWith("m_"))
            dailyQuizCallbackHandler.process(update);
        if (callbackQueryData.startsWith("lang_"))
            userRegisterCallbackHandler.process(update);
    }


}

