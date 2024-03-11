package com.pilog.url.redirector.controller;


import com.pilog.url.redirector.dto.ChatCompletionChunk;
import com.pilog.url.redirector.service.IAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class RedirectorController {

    @Autowired private IAIService iaiService;

    @GetMapping("/showAITypedValueResults")
    public ResponseEntity<String> chat(@RequestParam("prompt") String prompt) {

        String response = iaiService.showAITypedValueResults(prompt);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping(
            value = "/showAITypedValueResultsStream",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<String>> chatStream(@RequestParam("prompt") String prompt) {

        Flux<String> stringFlux = iaiService.showAITypedValueResultsStream(prompt);
        return new ResponseEntity(stringFlux, HttpStatus.OK);
    }

    @GetMapping(
            value = "/showAITypedValueResultsStreams",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<ChatCompletionChunk>> chatStreams(
            @RequestParam("prompt") String prompt) {
        Flux<ChatCompletionChunk> stringFlux = iaiService.showAITypedValueResultsStreams(prompt);
        return new ResponseEntity(stringFlux, HttpStatus.OK);
    }
}
