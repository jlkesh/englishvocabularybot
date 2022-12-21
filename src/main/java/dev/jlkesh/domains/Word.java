package dev.jlkesh.domains;


import dev.jlkesh.utils.parser.HTMLParser;
import dev.jlkesh.utils.parser.Tag;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Word {
    @NonNull
    private Long id;
    private String word;
    private String translations;
    private String definitions;
    private String examples;
    private int memorizedCount;
    @Builder.Default
    private int maxMemorizeThreshold = 5;
    private LocalDateTime createdAt;


    public void setCreatedAt(Date time) {
        this.createdAt = LocalDateTime.parse(time.toString());
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner("\n\n");
        stringJoiner.add(HTMLParser.parse("\uD83D\uDFE9 Word : ") + HTMLParser.parse(this.word));
        stringJoiner.add(HTMLParser.parse("Translations : ") + HTMLParser.parse(this.translations, Tag.SPOILER));
        stringJoiner.add(HTMLParser.parse("Definitions : ") + HTMLParser.parse(this.definitions, Tag.SPOILER));
        stringJoiner.add(HTMLParser.parse("Examples : ") + HTMLParser.parse(this.examples, Tag.SPOILER));
        stringJoiner.add(HTMLParser.parse("Memorized Count : ") + HTMLParser.parse(this.memorizedCount));
        return stringJoiner.toString();
    }
}
