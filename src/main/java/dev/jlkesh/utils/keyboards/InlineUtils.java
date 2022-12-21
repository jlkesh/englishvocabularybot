package dev.jlkesh.utils.keyboards;

import dev.jlkesh.domains.Word;
import dev.jlkesh.utils.Command;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InlineUtils {
    public static InlineKeyboardMarkup dailyQuizInlineButtonGenerator(Word word) {
        var board = new InlineKeyboardMarkup();
        var previous = new InlineKeyboardButton(Command.INLINE_PREVIOUS);
        var memorized = new InlineKeyboardButton(Command.INLINE_MEMORIZED);
        var next = new InlineKeyboardButton(Command.INLINE_NEXT);
        previous.setCallbackData("p_" + word.getId());
        memorized.setCallbackData("m_" + word.getId());
        next.setCallbackData("n_" + word.getId());
        board.setKeyboard(Collections.singletonList(Arrays.asList(previous, memorized, next)));
        return board;
    }

    public static InlineKeyboardMarkup languages() {
        var board = new InlineKeyboardMarkup();
        var ru = new InlineKeyboardButton(Command.RUSSIAN_TEXT);
        var uz = new InlineKeyboardButton(Command.UZBEK_TEXT);
        var en = new InlineKeyboardButton(Command.ENGLISH_TEXT);
        ru.setCallbackData(Command.RUSSIAN_CALLBACK);
        uz.setCallbackData(Command.UZBEK_CALLBACK);
        en.setCallbackData(Command.ENGLISH_CALLBACK);
        board.setKeyboard(List.of(
                Collections.singletonList(ru),
                Collections.singletonList(uz),
                Collections.singletonList(en)
        ));
        return board;
    }
}
