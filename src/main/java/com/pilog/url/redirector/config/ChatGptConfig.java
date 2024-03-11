package com.pilog.url.redirector.config;


import com.pilog.url.redirector.service.IBApplPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ChatGptConfig {
    @Autowired private IBApplPropertiesService ibApplPropertiesService;

    @Value(("${openai.api.url}"))
    private String apiURL;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        String chatGPTKey = ibApplPropertiesService.getChatGPTKey();
        restTemplate
                .getInterceptors()
                .add(
                        (request, body, execution) -> {
                            request.getHeaders().add("Authorization", "Bearer " + chatGPTKey);
                            return execution.execute(request, body);
                        });
        return restTemplate;
    }

    @Bean
    public WebClient webClient() {
        String openaiApiKey = ibApplPropertiesService.getChatGPTKey();
        return WebClient.builder()
                .baseUrl(apiURL)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + openaiApiKey)
                .build();
    }
}
