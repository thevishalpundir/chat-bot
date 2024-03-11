package com.pilog.url.redirector.dto;

import lombok.Data;

@Data
public class Choice {
    private  int index;
    private  Delta delta;
    private  Object logprobs;
    private String finish_reason;
}