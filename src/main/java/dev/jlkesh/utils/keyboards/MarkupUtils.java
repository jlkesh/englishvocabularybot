package dev.jlkesh.utils.keyboards;

import dev.jlkesh.utils.Command;
import dev.jlkesh.utils.MessageUtils;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Collections;

public class MarkupUtils {

    public static ReplyKeyboard mainMenu(String language) {
        var board = new ReplyKeyboardMarkup();
        board.setResizeKeyboard(true);
        var row = new KeyboardRow();
        var button1 = new KeyboardButton(MessageUtils.getTranslatedText("add.new.word", language));
        var button2 = new KeyboardButton(MessageUtils.getTranslatedText("start.daily.quiz", language));
        row.add(button1);
        row.add(button2);
        board.setKeyboard(Collections.singletonList(row));
        return board;
    }
}
