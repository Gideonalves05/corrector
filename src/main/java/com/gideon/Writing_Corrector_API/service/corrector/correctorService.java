package com.gideon.Writing_Corrector_API.service.corrector;

import com.gideon.Writing_Corrector_API.model.corrector.Essay;
import com.gideon.Writing_Corrector_API.model.repository.EssayRepository;
import com.gideon.Writing_Corrector_API.model.user.UserModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class correctorService {

    @Autowired
    private EssayRepository essayRepository;

    private final PromptService promptService;
    private final VertexAiGeminiChatModel chatModel;

    public correctorService(PromptService promptService, VertexAiGeminiChatModel chatModel) {
        this.promptService = promptService;
        this.chatModel = chatModel;
    }

    public Flux<String> getCorrectionFeedback(String text) {
        Prompt prompt = promptService.generatePrompt(text);
        return chatModel.stream(prompt)
                .map(response -> response.getResult().getOutput().getContent());
    }

    public Essay saveEssay(String title, String content, UserModel user) {
        Essay essay = Essay.builder()
                .title(title)
                .content(content)
                .submissionDate(LocalDateTime.now())
                .user(user)
                .build();
        return essayRepository.save(essay);
    }

    private Map<String, String> convert(List<ChatResponse> responses) {
        StringBuilder responseConverted = new StringBuilder();

        if (!responses.isEmpty()) {
            responses.forEach(chatResponse -> {
                responseConverted.append(chatResponse.getResult().getOutput().getContent()).append("\n");
            });
        } else {
            responseConverted.append("Flux is empty");
        }
        return Map.of("feedback", responseConverted.toString());
    }
}
