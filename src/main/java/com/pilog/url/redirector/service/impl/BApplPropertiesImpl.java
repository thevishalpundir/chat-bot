package com.pilog.url.redirector.service.impl;


import com.pilog.url.redirector.model.BApplProperties;
import com.pilog.url.redirector.repository.BApplPropertiesRepository;
import com.pilog.url.redirector.service.IBApplPropertiesService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BApplPropertiesImpl implements IBApplPropertiesService {
    @Autowired private BApplPropertiesRepository bApplPropertiesRepository;

    @Value("${spring.ai.openai.chat}")
    private String gptprocessValue;

    @Override
    public String getChatGPTKey() {

        Optional<BApplProperties> applProperties =
                bApplPropertiesRepository.findByKeyName(gptprocessValue);
        return applProperties
                .map(BApplProperties::getProcessValue)
                .orElseThrow(() -> new RuntimeException("Value not found in database"));
    }
}
