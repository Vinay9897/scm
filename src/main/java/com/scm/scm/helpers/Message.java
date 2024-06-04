package com.scm.scm.helpers;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    private String content;
    private MessageType type = MessageType.blue;
}
