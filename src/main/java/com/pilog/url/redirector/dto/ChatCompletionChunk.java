package com.pilog.url.redirector.dto;

import java.util.List;
import lombok.Data;

@Data
public class ChatCompletionChunk {
    private String id;
    private String object;
    private  long created;
    private  String model;
    private String system_fingerprint;
    private List<Choice> choices;


}