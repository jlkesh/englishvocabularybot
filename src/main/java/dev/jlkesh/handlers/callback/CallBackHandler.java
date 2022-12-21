package dev.jlkesh.handlers.callback;

import dev.jlkesh.VocabularyBuilderBot;
import dev.jlkesh.dao.UserDAO;
import dev.jlkesh.domains.DBUser;
import dev.jlkesh.handlers.Handler;
import dev.jlkesh.utils.keyboards.MarkupUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.*;

import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CallBackHandler implements Handler {
    @Getter
    private final static CallBackHandler instance = new CallBackHandler();
    private final Handler dailyQuizCallbackHandler = DailyQuizCallbackHandler.getInstance();
    private final Handler userRegisterCallbackHandler = DailyQuizCallbackHandler.getInstance();


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

