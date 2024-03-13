package com.pilog.url.redirector.service;


import com.pilog.url.redirector.dto.ApiDetailsDTO;
import com.pilog.url.redirector.dto.ChatCompletionChunk;
import reactor.core.publisher.Flux;

public interface IAIService {
    String showAITypedValueResults(String prompt);

    Flux<String> showAITypedValueResultsStream(String prompt);

    Flux<ChatCompletionChunk> showAITypedValueResultsStreams(String prompt);

    ApiDetailsDTO getApiDetails(String apiName);
}
