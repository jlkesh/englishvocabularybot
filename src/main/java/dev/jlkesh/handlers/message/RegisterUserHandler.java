package dev.jlkesh.handlers.message;

import dev.jlkesh.handlers.Handler;
import org.telegram.telegrambots.meta.api.objects.Update;

public class RegisterUserHandler implements Handler {

    private static Handler handler;

    @Override
    public void process(Update update) {

    }


    public static Handler getInstance() {
        if (handler == null) {
            handler = new RegisterUserHandler();
        }
        return handler;
    }
}
