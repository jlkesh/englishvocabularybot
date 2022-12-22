package dev.jlkesh.utils.keyboards;

import dev.jlkesh.domains.Word;
import dev.jlkesh.utils.Command;
import dev.jlkesh.utils.MessageUtils;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static dev.jlkesh.utils.Command.*;

public class InlineUtils {
    public static InlineKeyboardMarkup dailyQuizInlineButtonGenerator(Word word, String language) {
        var board = new InlineKeyboardMarkup();
        var previous = new InlineKeyboardButton(MessageUtils.getTranslatedText("previous", language));
        var memorized = new InlineKeyboardButton(MessageUtils.getTranslatedText("memorized", language));
        var next = new InlineKeyboardButton(MessageUtils.getTranslatedText("next", language));
        previous.setCallbackData("p_" + word.getId());
        memorized.setCallbackData("m_" + word.getId());
        next.setCallbackData("n_" + word.getId());
        board.setKeyboard(Collections.singletonList(Arrays.asList(previous, memorized, next)));
        return board;
    }

    public static InlineKeyboardMarkup languages(String language) {
        var board = new InlineKeyboardMarkup();
        var ru = new InlineKeyboardButton(MessageUtils.getTranslatedText("russian.text", language));
        var uz = new InlineKeyboardButton(MessageUtils.getTranslatedText("uzbek.text", language));
        var en = new InlineKeyboardButton(MessageUtils.getTranslatedText("english.text", language));
        ru.setCallbackData(RUSSIAN_CALLBACK);
        uz.setCallbackData(UZBEK_CALLBACK);
        en.setCallbackData(ENGLISH_CALLBACK);
        board.setKeyboard(List.of(
                Collections.singletonList(ru),
                Collections.singletonList(uz),
                Collections.singletonList(en)
        ));
        return board;
    }
}
