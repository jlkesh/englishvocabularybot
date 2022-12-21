package dev.jlkesh.handlers.message;

import dev.jlkesh.handlers.Handler;
import org.telegram.telegrambots.meta.api.objects.Update;

public class AddNewWordsHandler implements Handler {
    private static Handler instance;

    @Override
    public void process(Update update) {

    }

    public static Handler getInstance() {
        if (instance == null) {
            instance = new AddNewWordsHandler();
        }
        return instance;
    }
}
