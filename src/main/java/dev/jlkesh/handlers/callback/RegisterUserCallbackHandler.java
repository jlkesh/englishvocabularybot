package dev.jlkesh.handlers.callback;

import dev.jlkesh.VocabularyBuilderBot;
import dev.jlkesh.dao.UserDAO;
import dev.jlkesh.domains.DBUser;
import dev.jlkesh.handlers.Handler;
import dev.jlkesh.utils.MessageUtils;
import dev.jlkesh.utils.keyboards.MarkupUtils;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public class RegisterUserCallbackHandler implements Handler {

    @Getter
    private static Handler instance = new RegisterUserCallbackHandler();
    private final VocabularyBuilderBot bot = VocabularyBuilderBot.getInstance();
    private final UserDAO userDAO = UserDAO.getInstance();

    @Override
    public void process(Update update) throws SQLException {
        var callbackQuery = update.getCallbackQuery();
        var user = callbackQuery.getFrom();
        var message = callbackQuery.getMessage();
        deleteMessage(message);

        // saveUserToDatabase(message, user, callbackQuery.getData());
        var sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        String language = callbackQuery.getData().substring(5);
        sendMessage.setText(MessageUtils.getTranslatedText("successfully.registered", language));
        sendMessage.setReplyMarkup(MarkupUtils.mainMenu(language));
        bot.executeMessage(sendMessage);
    }

    private void saveUserToDatabase(Message message, User user, String callbackQueryData) {
        CompletableFuture.runAsync(() -> {
            try {
                String language = callbackQueryData.substring(5);
                var dbUser = DBUser.builder()
                        .chatId(message.getChatId().toString())
                        .firstName(user.getFirstName())
                        .language(language)
                        .status(DBUser.Status.ACTIVE)
                        .build();
                userDAO.save(dbUser);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void deleteMessage(Message message) {
        CompletableFuture.runAsync(() -> {
            var deleteMessage = new DeleteMessage();
            deleteMessage.setChatId(message.getChatId());
            deleteMessage.setMessageId(message.getMessageId());
            bot.executeMessage(deleteMessage);
        });
    }
}
