package dev.jlkesh.utils.parser;

import lombok.NonNull;

public class HTMLParser {
    public static String parse(@NonNull Object object) {
        return parse(object, Tag.BOLD);
    }

    public static String parse(@NonNull Object object, @NonNull Tag tag) {
        return tag.getValue().formatted(object);
    }
}
