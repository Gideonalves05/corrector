package com.gideon.Writing_Corrector_API.service.corrector;

import com.gideon.Writing_Corrector_API.model.repository.correctorRepository;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

@Service
public class correctorService {

    @Autowired
    private correctorRepository correctionRepository;

    private final PdfReaderService pdfReaderService;
    private final PromptService promptService;
    private final VertexAiGeminiChatModel chatModel;

    public correctorService(PdfReaderService pdfReaderService, PromptService promptService, VertexAiGeminiChatModel chatModel) {
        this.pdfReaderService = pdfReaderService;
        this.promptService = promptService;
        this.chatModel = chatModel;
    }

    public Flux<String> getCorrectionFeedback(String text) {
        Prompt prompt = promptService.generatePrompt(text); // Gere o prompt com o texto extraído
        return chatModel.stream(prompt)
                .map(response -> response.getResult().getOutput().getContent());
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
