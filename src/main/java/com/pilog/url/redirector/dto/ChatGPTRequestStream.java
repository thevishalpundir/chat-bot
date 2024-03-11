package com.pilog.url.redirector.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatGPTRequestStream {

    private String model;
    private List<Message> messages;

    private boolean stream;

    public ChatGPTRequestStream(String model, String prompt,boolean stream) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user",prompt));
        this.stream=stream;
    }

}
