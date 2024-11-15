package com.gideon.Writing_Corrector_API.controller.corrector;

import com.gideon.Writing_Corrector_API.service.corrector.FileTemp;
import com.gideon.Writing_Corrector_API.service.corrector.correctorService;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/text-corrector")
public class correctorController {

    @Autowired
    private correctorService correctionService;

    @Autowired
    private VertexAiGeminiChatModel chatModel;



    @PostMapping(value = "/correction", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> correctText(@RequestPart(name = "file") MultipartFile file) {
        try {
            Path path = FileTemp.store(file);

            // Chama o serviço de correção e recebe a resposta da IA.
            List<Generation> correctionFeedback = correctionService.corrigirRedacao(path.toString()).block();

            // Retorna uma resposta estruturada
            return ResponseEntity.ok(Map.of("message", "Correção realizada com sucesso", "feedback", correctionFeedback));

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o PDF.");
        }
    }


    @GetMapping(value = "/test-gemini", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<ChatResponse> testGemini(){
        Prompt prompt = new Prompt(new UserMessage("Por que o céu é azul?"));
        return chatModel.stream(prompt);
    }
}
