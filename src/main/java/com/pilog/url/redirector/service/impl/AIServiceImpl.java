package com.pilog.url.redirector.service.impl;


import com.pilog.url.redirector.dto.ChatCompletionChunk;
import com.pilog.url.redirector.dto.ChatGPTRequest;
import com.pilog.url.redirector.dto.ChatGPTRequestStream;
import com.pilog.url.redirector.dto.ChatGptResponse;
import com.pilog.url.redirector.service.IAIService;
import com.pilog.url.redirector.service.IBApplPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;


@Service
public class AIServiceImpl implements IAIService {
@Autowired
  private IBApplPropertiesService ibApplPropertiesService;
    @Value("${openai.model}")
    private String model;

    @Value(("${openai.api.url}"))
    private String apiURL;

    @Autowired
    private RestTemplate template;
    @Autowired
    private WebClient webClient;

    public String showAITypedValueResults(String prompt) {
        ChatGPTRequest request=new ChatGPTRequest(model, prompt);
         ChatGptResponse chatGptResponse = template.postForObject(apiURL, request, ChatGptResponse.class);
        return chatGptResponse.getChoices().get(0).getMessage().getContent();
    }

    public Flux<String> showAITypedValueResultsStream(@RequestParam("prompt") String prompt) {
        ChatGPTRequestStream request = new ChatGPTRequestStream(model, prompt, true);
        Flux<ChatCompletionChunk> chatCompletionChunkFlux = webClient.post()
                .accept(MediaType.TEXT_EVENT_STREAM)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToFlux(ChatCompletionChunk.class);

        System.out.println(chatCompletionChunkFlux);


        return webClient.post()
                .accept(MediaType.TEXT_EVENT_STREAM)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToFlux(ChatCompletionChunk.class) // Assuming ChatCompletionChunk has the content field
                .filter(chunk -> chunk.getChoices() != null && !chunk.getChoices().isEmpty() &&
                        chunk.getChoices().get(0).getDelta() != null &&
                        chunk.getChoices().get(0).getDelta().getContent() != null)
                .map(chunk -> chunk.getChoices().get(0).getDelta().getContent());
    }

    public Flux<ChatCompletionChunk> showAITypedValueResultsStreams(@RequestParam("prompt") String prompt) {
        ChatGPTRequestStream request = new ChatGPTRequestStream(model, prompt, true);
        Flux<ChatCompletionChunk> chatCompletionChunkFlux = webClient.post()
                .accept(MediaType.TEXT_EVENT_STREAM)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToFlux(ChatCompletionChunk.class);

       return chatCompletionChunkFlux;

    }

}



