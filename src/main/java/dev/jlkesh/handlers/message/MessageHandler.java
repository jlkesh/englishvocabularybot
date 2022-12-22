package dev.jlkesh.handlers.message;

import dev.jlkesh.VocabularyBuilderBot;
import dev.jlkesh.dao.UserDAO;
import dev.jlkesh.domains.DBUser;
import dev.jlkesh.domains.Word;
import dev.jlkesh.handlers.Handler;
import dev.jlkesh.trackers.RegisterUserStep;
import dev.jlkesh.trackers.Step;
import dev.jlkesh.utils.keyboards.InlineUtils;
import dev.jlkesh.utils.keyboards.MarkupUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static dev.jlkesh.utils.Command.*;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageHandler implements Handler {
    @Getter
    private final static MessageHandler instance = new MessageHandler();
    private final static VocabularyBuilderBot bot = VocabularyBuilderBot.getInstance();
    private final Handler registerUserHandler = RegisterUserHandler.getInstance();
    private final Handler addNewWordsHandler = AddNewWordsHandler.getInstance();
    private final UserDAO userDAO = UserDAO.getInstance();
    private final ConcurrentHashMap<String, ? extends Step> status = new ConcurrentHashMap<>();


    @Override
    public void process(Update update) throws SQLException {
        Message message = update.getMessage();
        String chatId = message.getChatId().toString();
        Step step = status.get(chatId);
        if (step instanceof RegisterUserStep)
            registerUserHandler.process(update);
        else if (step instanceof AddNewWordsHandler)
            addNewWordsHandler.process(update);
        else {
            String path = message.getText();
            System.out.println("path = " + path);
            switch (path) {
                case START -> startCommandHandler(message);
//                case START_DAILY_QUIZ -> dailyQuizHandler(message);
                default -> defaultCommandHandler(message);
            }
        }
    }

    private void addNewWordHandler(Message message) {
        var sendMessage = getSendMessage(message);
        sendMessage.setText("Add New Word");
        bot.executeMessage(sendMessage);
    }

    /*private void dailyQuizHandler(Message message) {

        SendMessage sendMessage = getSendMessage(message);
        sendMessage.setText(word.toString());
        sendMessage.setReplyMarkup(InlineUtils.dailyQuizInlineButtonGenerator(word));
        bot.executeMessage(sendMessage);
    }
*/

    private void defaultCommandHandler(Message message) {
        bot.executeMessage(getDeleteMessage(message));
    }


    private void startCommandHandler(Message message) throws SQLException {
        User user = message.getFrom();
        String language = user.getLanguageCode();
        String userID = message.getChatId().toString();
        Optional<DBUser> dbUserOptional = userDAO.findById(userID);
        if (dbUserOptional.isEmpty()) {
            var sendMessage = getSendMessage(message);
            sendMessage.setText("Welcome " + user.getFirstName());
            sendMessage.setReplyMarkup(InlineUtils.languages(language));
            bot.executeMessage(sendMessage);
            // TODO: 21/12/22  send language,set steps
        } else {
            var sendMessage = getSendMessage(message);
            sendMessage.setText("Welcome " + user.getFirstName());
            sendMessage.setReplyMarkup(MarkupUtils.mainMenu(language));
            bot.executeMessage(sendMessage);
        }
    }


    private DeleteMessage getDeleteMessage(Message message) {
        var deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(message.getChatId());
        deleteMessage.setMessageId(message.getMessageId());
        return deleteMessage;
    }

    private SendMessage getSendMessage(Message message) {
        var sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.enableHtml(true);
        return sendMessage;
    }
}
