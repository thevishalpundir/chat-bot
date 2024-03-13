package com.pilog.url.redirector.controller;


import com.pilog.url.redirector.dto.ChatCompletionChunk;
import com.pilog.url.redirector.enums.APIType;
import com.pilog.url.redirector.exception.BadRequestException;
import com.pilog.url.redirector.service.IAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping
public class RedirectorController {

    @Autowired private IAIService iaiService;

    @GetMapping(path = "/api/v1/getSuitableRes")
    public ResponseEntity<?> getSuitableRes(
            @RequestParam(value = "prompt", required = false) String prompt,
            @RequestParam(value = "apiType", required = false) String apiType) {
        if (prompt != null && apiType != null) {
            throw new BadRequestException(
                    HttpStatus.BAD_REQUEST,
                    "Please provide only one parameter: prompt or apiType.",
                    "select.one.parameter");
        }
        return prompt == null
                ? ResponseEntity.ok(iaiService.getApiDetails(apiType))
                : ResponseEntity.ok(iaiService.showAITypedValueResults(prompt));
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

    private String getResponse(APIType apiType, String prompt) {
        switch (apiType) {
            case gpt:
                return iaiService.showAITypedValueResults(prompt);
            case type_1:
                return "TYPE_1";
            case type_2:
                return "TYPE_2";
            default:
                return "Unsupported API type";
        }
    }
}
