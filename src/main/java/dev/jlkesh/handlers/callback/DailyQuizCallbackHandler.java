package dev.jlkesh.handlers.callback;

import dev.jlkesh.handlers.Handler;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.sql.SQLException;

public class DailyQuizCallbackHandler implements Handler {

    @Getter
    private static Handler instance = new DailyQuizCallbackHandler();

    @Override
    public void process(Update update) throws SQLException {
               /* long id = Long.parseLong(callbackQueryData.substring(2));
        Word w = MessageHandler.words.stream()
                .filter(word -> word.getId().equals(id))
                .findFirst().get();
        w.setMemorizedCount(w.getMemorizedCount() + 1);
        var sendMessage = new EditMessageText();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(w.toString());
        sendMessage.setMessageId(message.getMessageId());
        sendMessage.setReplyMarkup(InlineUtils.dailyQuizInlineButtonGenerator(w));
        sendMessage.enableHtml(true);
        bot.executeMessage(sendMessage);*/

    }
}
