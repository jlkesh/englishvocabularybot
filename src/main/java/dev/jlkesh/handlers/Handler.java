package dev.jlkesh.handlers;

import org.telegram.telegrambots.meta.api.objects.Update;

import java.sql.SQLException;

public interface Handler {

    void process(Update update) throws SQLException;

}
