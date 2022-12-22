package dev.jlkesh.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageUtils {

    private static final ResourceBundle en = ResourceBundle.getBundle("messages", Locale.forLanguageTag("en"));
    private static final ResourceBundle ru = ResourceBundle.getBundle("messages", Locale.forLanguageTag("ru"));
    private static final ResourceBundle uz = ResourceBundle.getBundle("messages", Locale.forLanguageTag("uz"));

    public static String getTranslatedText(String key, String language) {
        if ("uz".equalsIgnoreCase(language))
            return uz.getString(key);
        if ("ru".equalsIgnoreCase(language))
            return ru.getString(key);
        if ("en".equalsIgnoreCase(language))
            return en.getString(key);
        return ru.getString(key);
    }
}
