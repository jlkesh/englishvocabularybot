package dev.jlkesh.utils.parser;

import lombok.Getter;

@Getter
public enum Tag {
    BOLD("<strong>%s</strong>"),
    SPOILER("<span class=\"tg-spoiler\">%s</span>");
    private final String value;

    Tag(String value) {
        this.value = value;
    }
}
