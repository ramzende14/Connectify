package com.connectify.connectify10.helpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String content;

    @Builder.Default
    private MessageType type=MessageType.blue; 

    
}
