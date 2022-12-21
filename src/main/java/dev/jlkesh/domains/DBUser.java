package dev.jlkesh.domains;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DBUser {
    private String chatId;
    private String firstName;
    private Status status;
    private String language;


    @Getter
    public static enum Status {
        BANNED(-100),
        NOT_FULLY_REGISTERED(-50),
        ACTIVE(0);
        private final int access;

        Status(int access) {
            this.access = access;
        }
    }


}
