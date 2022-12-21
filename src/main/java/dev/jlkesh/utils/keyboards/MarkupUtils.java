package dev.jlkesh.utils.keyboards;

import dev.jlkesh.utils.Command;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Collections;

public class MarkupUtils {

    public static ReplyKeyboard mainMenu() {
        var board = new ReplyKeyboardMarkup();
        board.setResizeKeyboard(true);
        var row = new KeyboardRow();
        var button1 = new KeyboardButton(Command.ADD_WORD);
        var button2 = new KeyboardButton(Command.START_DAILY_QUIZ);
        row.add(button1);
        row.add(button2);
        board.setKeyboard(Collections.singletonList(row));
        return board;
    }
}
