package com.gideon.Writing_Corrector_API.service.corrector;

import com.gideon.Writing_Corrector_API.model.corrector.Essay;
import com.gideon.Writing_Corrector_API.model.repository.EssayRepository;
import com.gideon.Writing_Corrector_API.model.user.UserModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;


@Service
public class correctorService {


    @Autowired
    private EssayRepository essayRepository;

    private final PromptService promptService;
    public final VertexAiGeminiChatModel chatModel;

    public correctorService(PromptService promptService, VertexAiGeminiChatModel chatModel) {
        this.promptService = promptService;
        this.chatModel = chatModel;
    }

    public Flux<String> getCorrectionFeedback(String text, String title, UserModel user) {
        Prompt prompt = promptService.generatePrompt(text);
        StringBuffer responseConverted = new StringBuffer();
        Flux<String> correctionFlux = chatModel.stream(prompt)
                .map(response -> {
                    String feedback = response.getResult().getOutput().getContent();
                    System.out.println("Feedback: " + feedback);
                    responseConverted.append(feedback);
                    return feedback;
                }).doFinally(signalType -> {
                    saveEssay(title, text, user, responseConverted.toString()); // Save the essay with the feedback
                });
        return correctionFlux;
    }


    public Essay saveEssay(String title, String content, UserModel user, String feedback) {
        Essay essay = Essay.builder()
                .title(title)
                .content(content)
                .submissionDate(LocalDateTime.now())
                .user(user)
                .feedback(feedback)
                .build();
        return essayRepository.save(essay);
    }

}
